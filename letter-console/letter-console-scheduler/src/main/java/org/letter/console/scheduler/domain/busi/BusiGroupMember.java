package org.letter.console.scheduler.domain.busi;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;
import java.io.Serializable;

/**
 * BusiGroupMember
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "busi_group_member")
public class BusiGroupMember extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "The unique identifier of the business group member.")
    private Long id;

    @Column(name = "busi_group_id", nullable = false)
    @Schema(description = "The ID of the business group.")
    private Long busiGroupId;

    @Column(name = "user_group_id", nullable = false)
    @Schema(description = "The ID of the user group.")
    private Long userGroupId;

    @Column(name = "perm_flag", length = 2, nullable = false)
    @Schema(description = "Permission flag indicating read-only (ro) or read-write (rw) access.")
    private String permFlag;

}
