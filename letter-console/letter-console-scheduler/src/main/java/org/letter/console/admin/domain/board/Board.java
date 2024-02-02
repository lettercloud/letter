package org.letter.console.admin.domain.board;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * Board
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "board")
public class Board  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "The unique identifier of the dashboard board.")
    private Long id;

    @Column(name = "group_id", nullable = false)
    @Schema(description = "The ID of the business group associated with the dashboard board.")
    private Long groupId;

    @Column(name = "name", length = 191, nullable = false)
    @Schema(description = "The name of the dashboard board.")
    private String name;

    @Column(name = "ident", length = 200, nullable = false)
    @Schema(description = "The identifier of the dashboard board.")
    private String ident;

    @Column(name = "tags", length = 255, nullable = false)
    @Schema(description = "Tags associated with the dashboard board, separated by spaces.")
    private String tags;

    @Column(name = "public", nullable = false)
    @Schema(description = "Indicates if the dashboard board is public (1) or not (0).")
    private boolean isPublic;

    @Column(name = "built_in", nullable = false)
    @Schema(description = "Indicates if the dashboard board is built-in (1) or not (0).")
    private boolean builtIn;

    @Column(name = "hide", nullable = false)
    @Schema(description = "Indicates if the dashboard board is hidden (1) or not (0).")
    private boolean hide;
	
}
