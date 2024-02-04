package org.letter.console.admin.user.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * UserGroupMember
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "c_user_group_member")
public class UserGroupMember extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Schema(description = "The unique identifier of the user group member.")
	private Long id;

	@Column(name = "group_id")
	@Schema(description = "The ID of the user group.")
	private Long groupId;

	@Column(name = "user_id")
	@Schema(description = "The ID of the user.")
	private Long userId;

}
