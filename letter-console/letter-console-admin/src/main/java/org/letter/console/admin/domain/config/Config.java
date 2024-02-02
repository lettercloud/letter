package org.letter.console.admin.domain.config;


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
@Table(name = "configs")
public class Config  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "The unique identifier of the config.")
    private Long id;

    @Column(name = "ckey", length = 191, nullable = false)
    @Schema(description = "The key of the config.")
    private String key;

    @Column(name = "cval", columnDefinition = "TEXT", nullable = false)
    @Schema(description = "The value of the config.")
    private String value;
}
