package org.letter.console.admin.info.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.letter.console.admin.info.domain.vo.SystemVersion;
import org.letter.console.admin.utils.ServerResponse;
import org.letter.console.annotation.rest.AnonymousGetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * VersionController
 * @author letter
 */
@Slf4j
@RestController
@RequestMapping("/api/n9e/versions")
@RequiredArgsConstructor
@Tag(name = "system version")
public class VersionController {
	
	
    @Operation(summary = "current version")
    @AnonymousGetMapping(value = "/")
    public Object version() throws Exception {
       return ServerResponse.build(new SystemVersion(), "");
    }

}
