package org.letter.console.admin.group.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.letter.console.admin.group.service.BusiGroupService;
import org.letter.console.admin.utils.ServerResponse;
import org.letter.console.annotation.rest.AnonymousGetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BusiGroupController
 *
 * @author letter
 */
@Slf4j
@RestController
@RequestMapping("/api/n9e/busi-groups")
@RequiredArgsConstructor
@Tag(name = "业务组")
public class BusiGroupController {
	private BusiGroupService busiGroupService;

	@Operation(summary = "get business group list")
	@GetMapping(value = "/")
	public Object getGroup() throws Exception {
		return ServerResponse.build("", "");
	}

}
