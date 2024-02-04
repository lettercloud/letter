package org.letter.console.tasker.domain.alert;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * AlertCurEvent
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "alert_cur_event")
public class AlertCurEvent  extends BaseEntity implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "cate", length = 128, nullable = false)
    private String cate;

    @Column(name = "datasource_id", nullable = false)
    private Long datasourceId;

    @Column(name = "cluster", length = 128, nullable = false)
    private String cluster;

    @Column(name = "group_id", nullable = false)
    private Long groupId;

    @Column(name = "group_name", length = 255, nullable = false)
    private String groupName;

    @Column(name = "hash", length = 64, nullable = false)
    private String hash;

    @Column(name = "rule_id", nullable = false)
    private Long ruleId;

    @Column(name = "rule_name", length = 255, nullable = false)
    private String ruleName;

    @Column(name = "rule_note", length = 2048, nullable = false)
    private String ruleNote;

    @Column(name = "rule_prod", length = 255, nullable = false)
    private String ruleProd;

    @Column(name = "rule_algo", length = 255, nullable = false)
    private String ruleAlgo;

    @Column(name = "severity", nullable = false)
    private Integer severity;

    @Column(name = "prom_for_duration", nullable = false)
    private Integer promForDuration;

    @Column(name = "prom_ql", length = 8192, nullable = false)
    private String promQl;

    @Column(name = "prom_eval_interval", nullable = false)
    private Integer promEvalInterval;

    @Column(name = "callbacks", length = 255, nullable = false)
    private String callbacks;

    @Column(name = "runbook_url", length = 255)
    private String runbookUrl;

    @Column(name = "notify_recovered", nullable = false)
    private Boolean notifyRecovered;

    @Column(name = "notify_channels", length = 255, nullable = false)
    private String notifyChannels;

    @Column(name = "notify_groups", length = 255, nullable = false)
    private String notifyGroups;

    @Column(name = "notify_repeat_next", nullable = false)
    private Long notifyRepeatNext;

    @Column(name = "notify_cur_number", nullable = false)
    private Integer notifyCurNumber;

    @Column(name = "target_ident", length = 191, nullable = false)
    private String targetIdent;

    @Column(name = "target_note", length = 191, nullable = false)
    private String targetNote;

    @Column(name = "first_trigger_time")
    private Long firstTriggerTime;

    @Column(name = "trigger_time", nullable = false)
    private Long triggerTime;

    @Column(name = "trigger_value", length = 255, nullable = false)
    private String triggerValue;

    @Column(name = "annotations", columnDefinition = "TEXT", nullable = false)
    private String annotations;

    @Column(name = "rule_config", columnDefinition = "TEXT", nullable = false)
    private String ruleConfig;

    @Column(name = "tags", length = 1024, nullable = false)
    private String tags;
}
