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
@Table(name = "role_operation")
public class RoleOperation  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "The unique identifier of the role operation.")
    private Long id;

    @Column(name = "role_name", length = 128, nullable = false)
    @Schema(description = "The name of the role.")
    private String roleName;

    @Column(name = "operation", length = 191, nullable = false)
    @Schema(description = "The operation associated with the role.")
    private String operation;
	
}
