package org.letter.console.admin.user.service;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;


/**
 * TokenProviderService
 *
 * @author letter
 */
public interface TokenProviderService {


	/**
	 * 创建Token 设置永不过期，
	 * Token 的时间有效性转到Redis 维护
	 *
	 * @param authentication /
	 * @return /
	 */
	public String createToken(Authentication authentication);

	/**
	 * 依据Token 获取鉴权信息
	 *
	 * @param token /
	 * @return /
	 */
	Authentication getAuthentication(String token);

	public Claims getClaims(String token);

	/**
	 * @param token 需要检查的token
	 */
	public void checkRenewal(String token);

	public String getToken(HttpServletRequest request);

	/**
	 * 获取登录用户RedisKey
	 *
	 * @param token /
	 * @return key
	 */
	public String loginKey(String token);
}
