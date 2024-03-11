package org.letter.console.admin.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.letter.console.admin.user.domain.dto.JwtUserDto;
import org.letter.console.admin.user.domain.dto.UserLoginDto;
import org.letter.console.admin.user.service.UserCacheManager;
import org.letter.console.admin.user.service.UserService;
import org.letter.console.exception.BadRequestException;
import org.letter.console.exception.EntityNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author Zheng Jie
 * @date 2018-11-22
 */
@Slf4j
@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	private final UserService userService;
	private final UserCacheManager userCacheManager;

	@Override
	public JwtUserDto loadUserByUsername(String username) {
		JwtUserDto jwtUserDto = userCacheManager.getUserCache(username);
		if (jwtUserDto == null) {
			UserLoginDto user;
			try {
				user = userService.getLoginData(username);
			} catch (EntityNotFoundException e) {
				// SpringSecurity会自动转换UsernameNotFoundException为BadCredentialsException
				throw new UsernameNotFoundException(username, e);
			}
			if (user == null) {
				throw new UsernameNotFoundException("");
			} else {
				if (!user.getEnabled()) {
					throw new BadRequestException("账号未激活！");
				}
				jwtUserDto = new JwtUserDto(
					user,
					new ArrayList<>(),
					new ArrayList<>()
				);
				// 添加缓存数据
				userCacheManager.addUserCache(username, jwtUserDto);
			}
		}
		return jwtUserDto;
	}
}
