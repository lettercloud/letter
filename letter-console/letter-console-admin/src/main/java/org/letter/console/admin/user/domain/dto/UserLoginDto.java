package org.letter.console.admin.user.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author letter
 * @description 用户缓存时使用
 **/
@Getter
@Setter
public class UserLoginDto extends UserDto {

	private String password;

	private Boolean isAdmin;
}
