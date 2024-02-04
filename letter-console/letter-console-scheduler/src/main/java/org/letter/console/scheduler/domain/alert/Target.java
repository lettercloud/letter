package org.letter.console.scheduler.domain.alert;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * Target
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "target")
public class Target  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "The unique identifier of the target.")
    private Long id;

    @Column(name = "group_id", nullable = false)
    @Schema(description = "The ID of the business group associated with the target.")
    private Long groupId;

    @Column(name = "ident", length = 191, nullable = false)
    @Schema(description = "The identifier of the target.")
    private String ident;

    @Column(name = "note", length = 255, nullable = false)
    @Schema(description = "Additional note appended to alert events as a field.")
    private String note;

    @Column(name = "tags", length = 512, nullable = false)
    @Schema(description = "Tags appended to series data, separated by spaces.")
    private String tags;
	
}
