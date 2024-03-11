package org.letter.console.admin.user.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.letter.console.admin.user.domain.dto.JwtUserDto;
import org.letter.console.admin.user.domain.dto.OnlineUserDto;
import org.letter.console.utils.PageResult;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

/**
 * UserOnlineService
 *
 * @author letter
 */
public interface UserOnlineService {
	/**
	 * save
	 *
	 * @param jwtUserDto
	 * @param token
	 * @param request
	 */
	void save(JwtUserDto jwtUserDto, String token, HttpServletRequest request);

	/**
	 * 查询全部数据
	 *
	 * @param username
	 * @param pageable
	 * @return
	 */
	PageResult<OnlineUserDto> getAll(String username, Pageable pageable);

	/**
	 * 查询全部数据，不分页
	 *
	 * @param username
	 * @return
	 */
	List<OnlineUserDto> getAll(String username);

	/**
	 * 退出登录
	 *
	 * @param token
	 */
	void logout(String token);

	/**
	 * 导出
	 *
	 * @param all
	 * @param response
	 * @throws IOException
	 */
	void download(List<OnlineUserDto> all, HttpServletResponse response) throws IOException;

	/**
	 * 查询用户
	 *
	 * @param key
	 * @return
	 */
	OnlineUserDto getOne(String key);

	/**
	 * 根据用户名强退用户
	 *
	 * @param username
	 */
	void kickOutForUsername(String username);
}
