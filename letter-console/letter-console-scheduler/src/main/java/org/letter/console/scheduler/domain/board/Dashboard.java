package org.letter.console.scheduler.domain.board;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * Config
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "dashboard")
public class Dashboard  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "The unique identifier of the deprecated dashboard.")
    private Long id;

    @Column(name = "group_id", nullable = false)
    @Schema(description = "The ID of the business group associated with the deprecated dashboard.")
    private Long groupId;

    @Column(name = "name", length = 191, nullable = false)
    @Schema(description = "The name of the deprecated dashboard.")
    private String name;

    @Column(name = "tags", length = 255, nullable = false)
    @Schema(description = "Tags associated with the deprecated dashboard, separated by spaces.")
    private String tags;

    @Column(name = "configs", length = 8192)
    @Schema(description = "Dashboard variables (deprecated).")
    private String configs;

    @Column(name = "create_at", nullable = false)
    @Schema(description = "Timestamp of creation.")
    private Long createAt;

    @Column(name = "create_by", length = 64, nullable = false)
    @Schema(description = "Creator's username.")
    private String createBy;

    @Column(name = "update_at", nullable = false)
    @Schema(description = "Timestamp of last update.")
    private Long updateAt;

    @Column(name = "update_by", length = 64, nullable = false)
    @Schema(description = "Last updater's username.")
    private String updateBy;

}
