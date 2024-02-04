package org.letter.console.tasker.domain.source;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * NotifyTpl
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "notify_tpl")
public class NotifyTpl  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "channel", nullable = false, length = 32, unique = true)
    private String channel;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

}
