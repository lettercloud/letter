package org.letter.console.scheduler.domain.alert;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * AlertSubscribe
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "alert_subscribe")
public class AlertSubscribe  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "The unique identifier of the alert subscription.")
    private Long id;

    @Column(name = "name", length = 255, nullable = false)
    @Schema(description = "The name of the alert subscription.")
    private String name;

    @Column(name = "disabled", nullable = false)
    @Schema(description = "Indicates if the alert subscription is disabled.")
    private Boolean disabled;

    @Column(name = "group_id", nullable = false)
    @Schema(description = "The ID of the business group associated with the alert subscription.")
    private Long groupId;

    @Column(name = "prod", length = 255, nullable = false)
    @Schema(description = "The production environment associated with the alert subscription.")
    private String prod;

    @Column(name = "cate", length = 128, nullable = false)
    @Schema(description = "The category of the alert subscription.")
    private String cate;

    @Column(name = "datasource_ids", length = 255, nullable = false)
    @Schema(description = "The IDs of the datasources associated with the alert subscription, separated by spaces.")
    private String datasourceIds;

    @Column(name = "cluster", length = 128, nullable = false)
    @Schema(description = "The cluster associated with the alert subscription.")
    private String cluster;

    @Column(name = "rule_id", nullable = false)
    @Schema(description = "The ID of the alert rule associated with the subscription.")
    private Long ruleId;

    @Column(name = "severities", length = 32, nullable = false)
    @Schema(description = "Severities associated with the alert subscription.")
    private String severities;

    @Column(name = "tags", length = 4096, nullable = false)
    @Schema(description = "JSON string representing tags, key-value pairs.")
    private String tags;

    @Column(name = "redefine_severity", nullable = false)
    @Schema(description = "Indicates if the severity is redefined for the alert subscription.")
    private Boolean redefineSeverity;

    @Column(name = "new_severity", nullable = false)
    @Schema(description = "The new severity for the alert subscription.")
    private Integer newSeverity;

    @Column(name = "redefine_channels", nullable = false)
    @Schema(description = "Indicates if the channels are redefined for the alert subscription.")
    private Boolean redefineChannels;

    @Column(name = "new_channels", length = 255, nullable = false)
    @Schema(description = "New channels for the alert subscription, separated by spaces.")
    private String newChannels;

    @Column(name = "user_group_ids", length = 250, nullable = false)
    @Schema(description = "IDs of the user groups to notify, separated by spaces.")
    private String userGroupIds;

    @Column(name = "webhooks", columnDefinition = "TEXT", nullable = false)
    @Schema(description = "Webhooks associated with the alert subscription.")
    private String webhooks;

    @Column(name = "extra_config", columnDefinition = "TEXT", nullable = false)
    @Schema(description = "Extra configuration associated with the alert subscription.")
    private String extraConfig;

    @Column(name = "redefine_webhooks", nullable = false)
    @Schema(description = "Indicates if the webhooks are redefined for the alert subscription.")
    private Boolean redefineWebhooks;

    @Column(name = "for_duration", nullable = false)
    @Schema(description = "Duration of the subscription, in milliseconds.")
    private Long forDuration;
}
