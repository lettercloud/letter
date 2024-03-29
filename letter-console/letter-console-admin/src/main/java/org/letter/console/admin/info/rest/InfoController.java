package org.letter.console.admin.info.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.letter.console.admin.utils.ServerResponse;
import org.letter.console.annotation.rest.AnonymousGetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * InfoController
 *
 * @author letter
 */
@Slf4j
@RestController
@RequestMapping("/api/n9e/site-info")
@RequiredArgsConstructor
@Tag(name = "system information")
public class InfoController {


	@Operation(summary = "current information")
	@GetMapping(value = "")
	public Object status(@RequestParam("key") String key) throws Exception {
		return ServerResponse.build("", "");
	}

}
