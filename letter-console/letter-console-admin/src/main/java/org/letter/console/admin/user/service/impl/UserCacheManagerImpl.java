package org.letter.console.admin.user.service.impl;

import cn.hutool.core.util.RandomUtil;
import lombok.RequiredArgsConstructor;
import org.letter.console.admin.config.properties.UserLoginProperties;
import org.letter.console.admin.user.domain.dto.JwtUserDto;
import org.letter.console.admin.user.service.CacheProxy;
import org.letter.console.admin.user.service.UserCacheManager;
import org.letter.console.utils.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * UserCacheManagerImpl
 *
 * @author letter
 **/
@Component
@RequiredArgsConstructor
public class UserCacheManagerImpl implements UserCacheManager {


	private final CacheProxy cacheProxy;

	private final UserLoginProperties loginProperties;

	/**
	 * 返回用户缓存
	 *
	 * @param userName 用户名
	 * @return JwtUserDto
	 */
	public JwtUserDto getUserCache(String userName) {
		if (StringUtils.isNotEmpty(userName)) {
			// 获取数据
			Object obj = cacheProxy.get(UserLoginProperties.cacheKey + userName);
			if (obj != null) {
				return (JwtUserDto) obj;
			}
		}
		return null;
	}

	/**
	 * 添加缓存到Redis
	 *
	 * @param userName 用户名
	 */
	@Async
	public void addUserCache(String userName, JwtUserDto user) {
		if (StringUtils.isNotEmpty(userName)) {
			// 添加数据, 避免数据同时过期
			long time = loginProperties.getCacheIdleTime() + RandomUtil.randomInt(900, 1800);
			cacheProxy.set(UserLoginProperties.cacheKey + userName, user, time);
		}
	}

	/**
	 * 清理用户缓存信息
	 * 用户信息变更时
	 *
	 * @param userName 用户名
	 */
	@Async
	public void cleanUserCache(String userName) {
		if (StringUtils.isNotEmpty(userName)) {
			// 清除数据
			cacheProxy.del(UserLoginProperties.cacheKey + userName);
		}
	}
}