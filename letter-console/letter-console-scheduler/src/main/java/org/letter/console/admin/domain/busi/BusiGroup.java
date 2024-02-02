package org.letter.console.admin.domain.busi;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * BusiGroup
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "busi_group")
public class BusiGroup  extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Schema(description = "The unique identifier of the business group.")
	private Long id;

	@Column(name = "name", length = 191, nullable = false)
	@Schema(description = "The name of the business group.")
	private String name;

	@Column(name = "label_enable", nullable = false)
	@Schema(description = "Indicates if labels are enabled for the business group.")
	private boolean labelEnable;

	@Column(name = "label_value", length = 191, nullable = false)
	@Schema(description = "The value of the label associated with the business group if labelEnable is true.")
	private String labelValue;

}
