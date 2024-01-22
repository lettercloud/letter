package org.letter.console.modules.security.config;

import lombok.RequiredArgsConstructor;
import org.letter.console.annotation.AnonymousAccess;
import org.letter.console.modules.security.config.bean.SecurityProperties;
import org.letter.console.modules.security.security.JwtAccessDeniedHandler;
import org.letter.console.modules.security.security.JwtAuthenticationEntryPoint;
import org.letter.console.modules.security.security.TokenConfigurer;
import org.letter.console.modules.security.security.TokenProvider;
import org.letter.console.modules.security.service.OnlineUserService;
import org.letter.console.modules.security.service.UserCacheManager;
import org.letter.console.utils.enums.RequestMethodEnum;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.*;

/**
 * @author Zheng Jie
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SpringSecurityConfig {

	private final TokenProvider tokenProvider;
	private final CorsFilter corsFilter;
	private final JwtAuthenticationEntryPoint authenticationErrorHandler;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
	private final ApplicationContext applicationContext;
	private final SecurityProperties properties;
	private final OnlineUserService onlineUserService;
	private final UserCacheManager userCacheManager;

	@Bean
	GrantedAuthorityDefaults grantedAuthorityDefaults() {
		// 去除 ROLE_ 前缀
		return new GrantedAuthorityDefaults("");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		// 密码加密方式
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		// 搜寻匿名标记 url： @AnonymousAccess
		RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) applicationContext.getBean("requestMappingHandlerMapping");
		Map<RequestMappingInfo, HandlerMethod> handlerMethodMap = requestMappingHandlerMapping.getHandlerMethods();
		httpSecurity
			.csrf(csrfConfigurer -> {
				// 启用 CSRF 防护
				csrfConfigurer.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
			})
			.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
			.exceptionHandling(configurer -> {
				// 授权异常
				configurer.authenticationEntryPoint(authenticationErrorHandler);
				configurer.accessDeniedHandler(jwtAccessDeniedHandler);
			})
			.headers(headersConfigurer -> headersConfigurer.frameOptions(frameOptionsConfig -> {
				//disable frame option
				frameOptionsConfig.disable();
			}))
			.sessionManagement(managementConfigurer -> {
				//STATELESS
				managementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			})
			.authorizeRequests(registry -> {
					Map<String, Set<String>> anonymousUrls = getAnonymousUrl(handlerMethodMap);
					//静态资源
					registry.requestMatchers(HttpMethod.GET,
						"/*.html",
						"/**/*.html",
						"/**/*.css",
						"/**/*.js",
						"/webSocket/**").permitAll()
					// swagger 文档
					.requestMatchers("/swagger-ui.html").permitAll()
					.requestMatchers("/swagger-resources/**").permitAll()
					.requestMatchers("/webjars/**").permitAll()
					.requestMatchers("/*/api-docs").permitAll()
					// 文件
					.requestMatchers("/avatar/**").permitAll()
					.requestMatchers("/file/**").permitAll()
					// 阿里巴巴 druid
					.requestMatchers("/druid/**").permitAll()
					// 放行OPTIONS请求
					.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
					// 自定义匿名访问所有url放行：允许匿名和带Token访问，细腻化到每个 Request 类型
					// GET
					.requestMatchers(HttpMethod.GET, anonymousUrls.get(RequestMethodEnum.GET.getType()).toArray(new String[0])).permitAll()
					// POST
					.requestMatchers(HttpMethod.POST, anonymousUrls.get(RequestMethodEnum.POST.getType()).toArray(new String[0])).permitAll()
					// PUT
					.requestMatchers(HttpMethod.PUT, anonymousUrls.get(RequestMethodEnum.PUT.getType()).toArray(new String[0])).permitAll()
					// PATCH
					.requestMatchers(HttpMethod.PATCH, anonymousUrls.get(RequestMethodEnum.PATCH.getType()).toArray(new String[0])).permitAll()
					// DELETE
					.requestMatchers(HttpMethod.DELETE, anonymousUrls.get(RequestMethodEnum.DELETE.getType()).toArray(new String[0])).permitAll()
					// 所有类型的接口都放行
					.requestMatchers(anonymousUrls.get(RequestMethodEnum.ALL.getType()).toArray(new String[0])).permitAll()
					// 所有请求都需要认证
					.anyRequest().authenticated();
			})
			.apply(securityConfigurerAdapter());
		return httpSecurity.build();
	}

	private TokenConfigurer securityConfigurerAdapter() {
		return new TokenConfigurer(tokenProvider, properties, onlineUserService, userCacheManager);
	}

	private Map<String, Set<String>> getAnonymousUrl(Map<RequestMappingInfo, HandlerMethod> handlerMethodMap) {
		Map<String, Set<String>> anonymousUrls = new HashMap<>(8);
		Set<String> get = new HashSet<>();
		Set<String> post = new HashSet<>();
		Set<String> put = new HashSet<>();
		Set<String> patch = new HashSet<>();
		Set<String> delete = new HashSet<>();
		Set<String> all = new HashSet<>();
		for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethodMap.entrySet()) {
			HandlerMethod handlerMethod = infoEntry.getValue();
			AnonymousAccess anonymousAccess = handlerMethod.getMethodAnnotation(AnonymousAccess.class);
			if (null != anonymousAccess && infoEntry.getKey() != null && infoEntry.getKey().getPatternsCondition() != null) {
				List<RequestMethod> requestMethods = new ArrayList<>(infoEntry.getKey().getMethodsCondition().getMethods());
				RequestMethodEnum request = RequestMethodEnum.find(requestMethods.size() == 0 ? RequestMethodEnum.ALL.getType() : requestMethods.get(0).name());
				switch (Objects.requireNonNull(request)) {
					case GET:
						get.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
						break;
					case POST:
						post.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
						break;
					case PUT:
						put.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
						break;
					case PATCH:
						patch.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
						break;
					case DELETE:
						delete.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
						break;
					default:
						all.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
						break;
				}
			}
		}
		anonymousUrls.put(RequestMethodEnum.GET.getType(), get);
		anonymousUrls.put(RequestMethodEnum.POST.getType(), post);
		anonymousUrls.put(RequestMethodEnum.PUT.getType(), put);
		anonymousUrls.put(RequestMethodEnum.PATCH.getType(), patch);
		anonymousUrls.put(RequestMethodEnum.DELETE.getType(), delete);
		anonymousUrls.put(RequestMethodEnum.ALL.getType(), all);
		return anonymousUrls;
	}
}
