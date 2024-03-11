package org.letter.console.admin.user.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.letter.console.admin.config.properties.SecurityProperties;
import org.letter.console.admin.config.properties.UserLoginProperties;
import org.letter.console.admin.user.domain.dto.AuthUserDto;
import org.letter.console.admin.user.domain.dto.UserDto;
import org.letter.console.admin.user.service.AuthConfigService;
import org.letter.console.admin.user.service.TokenProviderService;
import org.letter.console.admin.user.service.UserOnlineService;
import org.letter.console.admin.user.service.UserService;
import org.letter.console.admin.utils.ServerResponse;
import org.letter.console.annotation.rest.AnonymousDeleteMapping;
import org.letter.console.annotation.rest.AnonymousGetMapping;
import org.letter.console.annotation.rest.AnonymousPostMapping;
import org.letter.console.utils.RsaUtils;
import org.letter.console.utils.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * AuthController
 *
 * @author letter
 */
@Slf4j
@RestController
@RequestMapping("/api/n9e/auth")
@RequiredArgsConstructor
@Tag(name = "系统鉴权及配置")
public class AuthController {
	private final TokenProviderService tokenProviderService;
	private final UserService userService;
	private final UserOnlineService userOnlineService;
	private final AuthConfigService authConfigService;

	@Operation(summary = "sso配置信息")
	@AnonymousGetMapping(value = "/sso-config")
	public Object ssoConfig() throws Exception {
		return ServerResponse.build(authConfigService.getSsoConfig(), "");
	}

	@Operation(summary = "是否显示验证码")
	@AnonymousGetMapping(value = "/ifshowcaptcha")
	public Object showCaptcha() throws Exception {
		return ServerResponse.build(authConfigService.getShowCaptcha(), "");
	}

	@Operation(summary = "rsa配置信息")
	@AnonymousGetMapping(value = "/rsa-config")
	public Object rsaConfig() throws Exception {
		return ServerResponse.build(authConfigService.getRsaConfig(), "");
	}

	@Operation(summary = "登录授权")
	@AnonymousPostMapping(value = "/login")
	public ResponseEntity<Object> login(@Validated @RequestBody AuthUserDto authUser, HttpServletRequest request) throws Exception {
		// 密码解密
		String password = authUser.getPassword();
		;
		if (authConfigService.getRsaConfig().isOpenRSA()) {
			password = RsaUtils.decryptByPrivateKey(authConfigService.getRsaPrivateKey(), authUser.getPassword());
		}
		UsernamePasswordAuthenticationToken authenticationToken =
			new UsernamePasswordAuthenticationToken(authUser.getUsername(), password);
		Authentication authentication = authenticationToken;
		String token = tokenProviderService.createToken(authentication);
		final UserDto userDto = userService.findByName(authUser.getUsername());
		// 返回 token 与 用户信息
		Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
			put("access_token", token);
			put("refresh_token", token);
			put("user", userDto);
		}};
		// 返回登录信息
		return ResponseEntity.ok(authInfo);
	}

	@Operation(summary = "获取用户信息")
	@GetMapping(value = "/info")
	public ResponseEntity<UserDetails> getUserInfo() {
		return ResponseEntity.ok(SecurityUtils.getCurrentUser());
	}


	@Operation(summary = "退出登录")
	@AnonymousDeleteMapping(value = "/logout")
	public ResponseEntity<Object> logout(HttpServletRequest request) {
		userOnlineService.logout(tokenProviderService.getToken(request));
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
