package org.letter.console.admin.user.domain.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseDTO;

import java.io.Serializable;
import java.util.Date;

/**
 * @author letter
 */
@Getter
@Setter
public class UserDto extends BaseDTO implements Serializable {

	private Long id;


	private String username;

	private String nickName;

	private String email;

	private String phone;


	@JSONField(serialize = false)
	@JsonIgnore
	private transient String password;

	private Boolean enabled;

	@JSONField(serialize = false)
	private Boolean isAdmin = false;

	private Date pwdResetTime;
}
