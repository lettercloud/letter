package org.letter.console.admin.user.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.letter.console.admin.user.domain.dto.UserDto;
import org.letter.console.admin.user.service.UserService;
import org.letter.console.admin.utils.ServerResponse;
import org.letter.console.annotation.rest.AnonymousGetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SelfController
 *
 * @author letter
 */
@Slf4j
@RestController
@RequestMapping("/api/n9e/self")
@RequiredArgsConstructor
@Tag(name = "配置")
public class SelfController {
	private final UserService userService;

	@Operation(summary = "个人信息")
	@GetMapping(value = "/profile")
	public Object ssoConfig() throws Exception {
		return ServerResponse.build(new UserDto(), "");
	}

}
