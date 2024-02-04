package org.letter.console.admin.user.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author letter
 */
@Getter
@Setter
public class AuthUserDto {

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	private String code;

	private String uuid = "";
}
