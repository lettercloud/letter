package org.letter.console.admin.domain.alert;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * TaskTpl
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "task_tpl")
public class TaskTpl  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "group_id", nullable = false)
    private Integer groupId;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "account", nullable = false, length = 64)
    private String account;

    @Column(name = "batch", nullable = false)
    private Integer batch;

    @Column(name = "tolerance", nullable = false)
    private Integer tolerance;

    @Column(name = "timeout", nullable = false)
    private Integer timeout;

    @Column(name = "pause", nullable = false, length = 255)
    private String pause;

    @Column(name = "script", nullable = false, columnDefinition = "TEXT")
    private String script;

    @Column(name = "args", nullable = false, length = 512)
    private String args;

    @Column(name = "tags", nullable = false, length = 255)
    private String tags;
}
