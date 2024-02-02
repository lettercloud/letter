package org.letter.console.admin.domain.board;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * ChartShare
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "chart_share")
public class ChartShare  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "The unique identifier of the deprecated chart share.")
    private Long id;

    @Column(name = "cluster", length = 128, nullable = false)
    @Schema(description = "The cluster associated with the deprecated chart share.")
    private String cluster;

    @Column(name = "datasource_id", nullable = false)
    @Schema(description = "The ID of the datasource associated with the deprecated chart share.")
    private Long datasourceId;

    @Column(name = "configs", columnDefinition = "TEXT")
    @Schema(description = "The configurations of the deprecated chart share.")
    private String configs;

    @Column(name = "create_at", nullable = false)
    @Schema(description = "Timestamp of creation.")
    private Long createAt;

    @Column(name = "create_by", length = 64, nullable = false)
    @Schema(description = "Creator's username.")
    private String createBy;

}
