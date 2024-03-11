package org.letter.console.admin.user.service;

import org.letter.console.admin.user.domain.dto.JwtUserDto;


/**
 * UserCacheManager
 *
 * @author letter
 */
public interface UserCacheManager {


	/**
	 * 返回用户缓存
	 *
	 * @param userName 用户名
	 * @return JwtUserDto
	 */
	JwtUserDto getUserCache(String userName);

	/**
	 * 添加缓存到Redis
	 *
	 * @param userName 用户名
	 */
	void addUserCache(String userName, JwtUserDto user);

	/**
	 * 清理用户缓存信息
	 * 用户信息变更时
	 *
	 * @param userName 用户名
	 */
	void cleanUserCache(String userName);
}