package org.letter.console.admin.user.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.letter.console.admin.config.UserRsaProperties;
import org.letter.console.admin.user.domain.User;
import org.letter.console.admin.user.service.UserService;
import org.letter.console.admin.user.domain.dto.UserDto;
import org.letter.console.admin.user.domain.dto.UserQueryCriteria;
import org.letter.console.exception.BadRequestException;
import org.letter.console.utils.PageResult;
import org.letter.console.utils.RsaUtils;
import org.letter.console.utils.SecurityUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

/**
 * UserController
 * 
 * @author letter
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final PasswordEncoder passwordEncoder;
	private final UserService userService;
	private final UserRsaProperties userRsaProperties;

	@Operation(summary = "导出用户数据")
	@GetMapping(value = "/download")
	@PreAuthorize("@el.check('user:list')")
	public void exportUser(HttpServletResponse response, UserQueryCriteria criteria) throws IOException {
		userService.download(userService.queryAll(criteria), response);
	}

	@Operation(summary = "查询用户")
	@GetMapping
	@PreAuthorize("@el.check('user:list')")
	public ResponseEntity<PageResult<UserDto>> queryUser(UserQueryCriteria criteria, Pageable pageable) {
		return new ResponseEntity<>(userService.queryAll(criteria, pageable), HttpStatus.OK);

	}

	@Operation(summary = "新增用户")
	@PostMapping
	@PreAuthorize("@el.check('user:add')")
	public ResponseEntity<Object> createUser(@Validated @RequestBody User resources) {
		// 默认密码 123456
		resources.setPassword(passwordEncoder.encode("123456"));
		userService.create(resources);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@Operation(summary = "修改用户")
	@PutMapping
	@PreAuthorize("@el.check('user:edit')")
	public ResponseEntity<Object> updateUser(@Validated(User.Update.class) @RequestBody User resources) throws Exception {
		userService.update(resources);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Operation(summary = "修改用户：个人中心")
	@PutMapping(value = "center")
	public ResponseEntity<Object> centerUser(@Validated(User.Update.class) @RequestBody User resources) {
		if (!resources.getId().equals(SecurityUtils.getCurrentUserId())) {
			throw new BadRequestException("不能修改他人资料");
		}
		userService.updateCenter(resources);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Operation(summary = "删除用户")
	@DeleteMapping
	@PreAuthorize("@el.check('user:del')")
	public ResponseEntity<Object> deleteUser(@RequestBody Set<Long> ids) {
		userService.delete(ids);
		return new ResponseEntity<>(HttpStatus.OK);
	}

//	@Operation(summary = "修改密码")
//	@PostMapping(value = "/updatePass")
//	public ResponseEntity<Object> updateUserPass(@RequestBody UserPassVo passVo) throws Exception {
//		String oldPass = RsaUtils.decryptByPrivateKey(rsaProperties.getPrivateKey(), passVo.getOldPass());
//		String newPass = RsaUtils.decryptByPrivateKey(rsaProperties.getPrivateKey(), passVo.getNewPass());
//		UserDto user = userService.findByName(SecurityUtils.getCurrentUsername());
//		if (!passwordEncoder.matches(oldPass, user.getPassword())) {
//			throw new BadRequestException("修改失败，旧密码错误");
//		}
//		if (passwordEncoder.matches(newPass, user.getPassword())) {
//			throw new BadRequestException("新密码不能与旧密码相同");
//		}
//		userService.updatePass(user.getUsername(), passwordEncoder.encode(newPass));
//		return new ResponseEntity<>(HttpStatus.OK);
//	}

	@Operation(summary = "重置密码")
	@PutMapping(value = "/resetPwd")
	public ResponseEntity<Object> resetPwd(@RequestBody Set<Long> ids) {
		String pwd = passwordEncoder.encode("123456");
		userService.resetPwd(ids, pwd);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Operation(summary = "修改头像")
	@PostMapping(value = "/updateAvatar")
	public ResponseEntity<Object> updateUserAvatar(@RequestParam MultipartFile avatar) {
		return new ResponseEntity<>(userService.updateAvatar(avatar), HttpStatus.OK);
	}

	@Operation(summary = "修改邮箱")
	@PostMapping(value = "/updateEmail/{code}")
	public ResponseEntity<Object> updateUserEmail(@PathVariable String code, @RequestBody User user) throws Exception {
		String password = RsaUtils.decryptByPrivateKey(userRsaProperties.getPrivateKey(), user.getPassword());
		UserDto userDto = userService.findByName(SecurityUtils.getCurrentUsername());
		if (!passwordEncoder.matches(password, userDto.getPassword())) {
			throw new BadRequestException("密码错误");
		}
		userService.updateEmail(userDto.getUsername(), user.getEmail());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
