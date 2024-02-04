package org.letter.console.scheduler.domain.alert;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * TaskTplHost
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "task_tpl_host")
public class TaskTplHost  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ii")
    private Integer ii;

    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "host", nullable = false, length = 128)
    private String host;
}
