package org.letter.console.admin.user.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * SsoConfig
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "c_sso_config")
public class SsoConfig extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "name", nullable = false, length = 191, unique = true)
	private String name;

	@Column(name = "content", nullable = false, columnDefinition = "TEXT")
	private String content;

}
