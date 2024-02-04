package org.letter.console.admin.user.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * User
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "c_users")
public class User extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Schema(description = "The unique identifier of the user.")
	private Long id;

	@Column(name = "username", length = 64, nullable = false)
	@Schema(description = "Login name, cannot rename")
	private String username;

	@Column(name = "nickname", length = 64, nullable = false)
	@Schema(description = "Display name, Chinese name")
	private String nickname;

	@Column(name = "password", length = 128, nullable = false)
	@Schema(description = "Password")
	private String password;

	@Column(name = "phone", length = 16, nullable = false)
	@Schema(description = "Phone number")
	private String phone;

	@Column(name = "email", length = 64, nullable = false)
	@Schema(description = "Email address")
	private String email;

	@Column(name = "portrait", length = 255, nullable = false)
	@Schema(description = "Portrait image URL")
	private String portrait;

	@Column(name = "roles", length = 255, nullable = false)
	@Schema(description = "Roles separated by spaces, e.g., Admin Standard Guest")
	private String roles;

	@Column(name = "contacts", length = 1024)
	@Schema(description = "Contacts in JSON format, e.g., {wecom:xx, dingtalk_robot_token:yy}")
	private String contacts;

	@Column(name = "maintainer", nullable = false)
	@Schema(description = "Indicates if the user is a maintainer")
	private boolean maintainer;

}
