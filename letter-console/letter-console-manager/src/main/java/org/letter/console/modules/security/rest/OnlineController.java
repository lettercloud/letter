package org.letter.console.modules.security.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.letter.console.modules.security.service.OnlineUserService;
import org.letter.console.modules.security.service.dto.OnlineUserDto;
import org.letter.console.utils.EncryptUtils;
import org.letter.console.utils.PageResult;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @author letter
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/online")
@Tag(name = "系统：在线用户管理")
public class OnlineController {

    private final OnlineUserService onlineUserService;

    @Operation(summary = "查询在线用户")
    @GetMapping
    @PreAuthorize("@el.check()")
    public ResponseEntity<PageResult<OnlineUserDto>> queryOnlineUser(String username, Pageable pageable){
        return new ResponseEntity<>(onlineUserService.getAll(username, pageable),HttpStatus.OK);
    }

    @Operation(summary = "导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check()")
    public void exportOnlineUser(HttpServletResponse response, String username) throws IOException {
        onlineUserService.download(onlineUserService.getAll(username), response);
    }

    @Operation(summary = "踢出用户")
    @DeleteMapping
    @PreAuthorize("@el.check()")
    public ResponseEntity<Object> deleteOnlineUser(@RequestBody Set<String> keys) throws Exception {
        for (String token : keys) {
            // 解密Key
            token = EncryptUtils.desDecrypt(token);
            onlineUserService.logout(token);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
