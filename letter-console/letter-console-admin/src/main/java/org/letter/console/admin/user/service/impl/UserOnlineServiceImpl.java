package org.letter.console.admin.user.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.letter.console.admin.config.SecurityProperties;
import org.letter.console.admin.user.service.TokenProviderService;
import org.letter.console.admin.user.service.UserOnlineService;
import org.letter.console.admin.user.domain.dto.JwtUserDto;
import org.letter.console.admin.user.domain.dto.OnlineUserDto;
import org.letter.console.utils.*;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * UserOnlineServiceImpl
 *
 * @author letter
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserOnlineServiceImpl implements UserOnlineService {

	private final SecurityProperties properties;
	private final TokenProviderService tokenProvider;

	/**
	 * 保存在线用户信息
	 *
	 * @param jwtUserDto /
	 * @param token      /
	 * @param request    /
	 */
	public void save(JwtUserDto jwtUserDto, String token, HttpServletRequest request) {

		try {
			String dept = "";
			//dept = jwtUserDto.getUser().getDept().getName();
			String ip = StringUtils.getIp(request);
			String browser = StringUtils.getBrowser(request);
			String address = StringUtils.getCityInfo(ip);
			OnlineUserDto onlineUserDto = new OnlineUserDto(jwtUserDto.getUsername(),
				jwtUserDto.getUser().getNickName(), dept, browser,
				ip, address, EncryptUtils.desEncrypt(token), new Date());
			String loginKey = tokenProvider.loginKey(token);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * 查询全部数据
	 *
	 * @param username /
	 * @param pageable /
	 * @return /
	 */
	public PageResult<OnlineUserDto> getAll(String username, Pageable pageable) {
		List<OnlineUserDto> onlineUserDtos = getAll(username);
		return PageUtil.toPage(
			PageUtil.paging(pageable.getPageNumber(),
				pageable.getPageSize(), onlineUserDtos),
			onlineUserDtos.size()
		);
	}

	/**
	 * 查询全部数据，不分页
	 *
	 * @param username /
	 * @return /
	 */
	public List<OnlineUserDto> getAll(String username) {
		String loginKey = properties.getOnlineKey() +
			(StringUtils.isBlank(username) ? "" : "*" + username);
//        List<String> keys = redisUtils.scan(loginKey + "*");
//        Collections.reverse(keys);
		List<OnlineUserDto> onlineUserDtos = new ArrayList<>();
//        for (String key : keys) {
//            onlineUserDtos.add((OnlineUserDto) redisUtils.get(key));
//        }
		onlineUserDtos.sort((o1, o2) -> o2.getLoginTime().compareTo(o1.getLoginTime()));
		return onlineUserDtos;
	}

	/**
	 * 退出登录
	 *
	 * @param token /
	 */
	public void logout(String token) {
		String loginKey = tokenProvider.loginKey(token);
//        redisUtils.del(loginKey);
	}

	/**
	 * 导出
	 *
	 * @param all      /
	 * @param response /
	 * @throws IOException /
	 */
	public void download(List<OnlineUserDto> all, HttpServletResponse response) throws IOException {
		List<Map<String, Object>> list = new ArrayList<>();
		for (OnlineUserDto user : all) {
			Map<String, Object> map = new LinkedHashMap<>();
			map.put("用户名", user.getUserName());
			map.put("部门", user.getDept());
			map.put("登录IP", user.getIp());
			map.put("登录地点", user.getAddress());
			map.put("浏览器", user.getBrowser());
			map.put("登录日期", user.getLoginTime());
			list.add(map);
		}
		FileUtil.downloadExcel(list, response);
	}

	/**
	 * 查询用户
	 *
	 * @param key /
	 * @return /
	 */
	public OnlineUserDto getOne(String key) {
		return null;
//        return (OnlineUserDto)redisUtils.get(key);
	}

	/**
	 * 根据用户名强退用户
	 *
	 * @param username /
	 */
	@Async
	public void kickOutForUsername(String username) {
		String loginKey = properties.getOnlineKey() + username + "*";
	}
}
