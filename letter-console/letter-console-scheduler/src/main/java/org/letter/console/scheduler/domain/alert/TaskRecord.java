package org.letter.console.scheduler.domain.alert;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * TaskRecord
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "task_record")
public class TaskRecord  extends BaseEntity implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "event_id", nullable = false)
    private Long eventId;

    @Column(name = "group_id", nullable = false)
    private Long groupId;

    @Column(name = "ibex_address", nullable = false, length = 128)
    private String ibexAddress;

    @Column(name = "ibex_auth_user", nullable = false, length = 128)
    private String ibexAuthUser;

    @Column(name = "ibex_auth_pass", nullable = false, length = 128)
    private String ibexAuthPass;

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
	
}
