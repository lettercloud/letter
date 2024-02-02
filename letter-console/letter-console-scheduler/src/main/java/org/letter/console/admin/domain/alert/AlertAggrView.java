package org.letter.console.admin.domain.alert;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * AlertAggrView
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "alert_aggr_view")
public class AlertAggrView  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "The unique identifier of the alert aggregation view.")
    private Long id;

    @Column(name = "name", length = 191, nullable = false)
    @Schema(description = "The name of the alert aggregation view.")
    private String name;

    @Column(name = "rule", length = 2048, nullable = false)
    @Schema(description = "The rule associated with the alert aggregation view.")
    private String rule;

    @Column(name = "cate", nullable = false)
    @Schema(description = "Category of the alert aggregation view. 0: preset, 1: custom")
    private Integer cate;
}
