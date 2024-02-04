package org.letter.console.tasker.domain.board;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * BoardPayload
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "board_payload")
public class BoardPayload  extends BaseEntity implements Serializable {

    @Id
    @Column(name = "id")
    @Schema(description = "The ID of the dashboard associated with the payload.")
    private Long id;

    @Column(name = "payload", columnDefinition = "MEDIUMTEXT", nullable = false)
    @Schema(description = "The payload data of the dashboard.")
    private String payload;

}
