package org.letter.console.admin.domain.board;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * Chart
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "chart")
public class Chart  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "The unique identifier of the deprecated chart.")
    private Long id;

    @Column(name = "group_id", nullable = false)
    @Schema(description = "The ID of the chart group associated with the deprecated chart.")
    private Long groupId;

    @Column(name = "configs", columnDefinition = "TEXT")
    @Schema(description = "The configurations of the deprecated chart.")
    private String configs;

    @Column(name = "weight", nullable = false)
    @Schema(description = "The weight of the deprecated chart.")
    private Integer weight;
}

