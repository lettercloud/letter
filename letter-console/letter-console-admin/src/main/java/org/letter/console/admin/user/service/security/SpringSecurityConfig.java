package org.letter.console.admin.user.service.security;

import lombok.RequiredArgsConstructor;
import org.letter.console.admin.config.properties.SecurityProperties;
import org.letter.console.admin.user.service.UserCacheManager;
import org.letter.console.admin.user.service.UserService;
import org.letter.console.annotation.AnonymousAccess;
import org.letter.console.utils.enums.RequestMethodEnum;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
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
 * SpringSecurityConfig
 *
 * @author letter
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SpringSecurityConfig {
	private final CorsFilter corsFilter;
	private final ApplicationContext applicationContext;
	
	private final UserService userService;
	private final UserCacheManager userCacheManager;

	private final JwtAuthenticationEntryPoint authenticationErrorHandler;
	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

	/**
	 * loader from db
	 * @return
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl(userService, userCacheManager);
	}

	/**
	 * 密码加密方式
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	
	/**
	 * url过滤
	 * 
	 * @param httpSecurity
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SecurityFilterChain filterUrlChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
//			.csrf(csrfConfigurer -> {
//				// 启用 CSRF 防护
//				csrfConfigurer.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
//			})
//			.addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
//			.exceptionHandling(configurer -> {
//				// 授权异常
//				configurer.authenticationEntryPoint(authenticationErrorHandler);
//				configurer.accessDeniedHandler(jwtAccessDeniedHandler);
//			})
//			.headers(headersConfigurer -> headersConfigurer.frameOptions(frameOptionsConfig -> {
//				//disable frame option
//				frameOptionsConfig.disable();
//			}))
//			.sessionManagement(managementConfigurer -> {
//				//STATELESS
//				managementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//			})
			.authorizeRequests(registry -> {
				//静态资源
				registry.requestMatchers(HttpMethod.GET,
						"/**.html",
						"/**.html",
						"/**.css",
						"/**.js").permitAll()
					//login
					.requestMatchers("/api/n9e/auth/**").permitAll()	
					// swagger 文档
					.requestMatchers("/swagger-ui/**").permitAll()
					.requestMatchers("/v3/api-docs/**").permitAll()
					.requestMatchers("/*/api-docs").permitAll()
					// 放行OPTIONS请求
					.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
					// 所有请求都需要认证
					.anyRequest().authenticated();
			});
		return httpSecurity.build();
	}

	
}
