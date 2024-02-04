package org.letter.console.admin.user.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * UserGroup
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "c_user_group")
public class UserGroup extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Schema(description = "The unique identifier of the user group.")
	private Long id;

	@Column(name = "p_id")
	@Schema(description = "The ID of the user group.")
	private Long pid;

	@Column(name = "name", length = 128, nullable = false)
	@Schema(description = "The name of the user group.")
	private String name;

	@Column(name = "note", length = 255, nullable = false)
	@Schema(description = "Additional notes about the user group.")
	private String note;

}
