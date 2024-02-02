package org.letter.console.admin.domain.board;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * MetricView
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "metric_view")
public class MetricView  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "The unique identifier of the metric view.")
    private Long id;

    @Column(name = "name", length = 191, nullable = false)
    @Schema(description = "The name of the metric view.")
    private String name;

    @Column(name = "cate", nullable = false)
    @Schema(description = "Category of the metric view. 0: preset, 1: custom")
    private Integer cate;

    @Column(name = "configs", length = 8192, nullable = false)
    @Schema(description = "Configuration details of the metric view.")
    private String configs;
	
}
