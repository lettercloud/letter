package org.letter.console.scheduler.domain.board;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * ChartGroup
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "chart_group")
public class ChartGroup  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "The unique identifier of the deprecated chart group.")
    private Long id;

    @Column(name = "dashboard_id", nullable = false)
    @Schema(description = "The ID of the dashboard associated with the deprecated chart group.")
    private Long dashboardId;

    @Column(name = "name", length = 255, nullable = false)
    @Schema(description = "The name of the deprecated chart group.")
    private String name;

    @Column(name = "weight", nullable = false)
    @Schema(description = "The weight of the deprecated chart group.")
    private Integer weight;
	
}
