package org.letter.console.admin.domain.user;

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
@Table(name = "role")
public class Role extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "The unique identifier of the role.")
    private Long id;

    @Column(name = "name", length = 191, nullable = false)
    @Schema(description = "The name of the role.")
    private String name;

    @Column(name = "note", length = 255, nullable = false)
    @Schema(description = "Additional notes about the role.")
    private String note;
	
}
