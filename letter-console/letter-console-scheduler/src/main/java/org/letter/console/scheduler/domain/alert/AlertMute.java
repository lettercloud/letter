package org.letter.console.scheduler.domain.alert;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * AlertMute
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "alert_mute")
public class AlertMute  extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "The unique identifier of the alert mute.")
    private Long id;

    @Column(name = "group_id", nullable = false)
    @Schema(description = "The ID of the business group associated with the alert mute.")
    private Long groupId;

    @Column(name = "prod", length = 255, nullable = false)
    @Schema(description = "The production environment associated with the alert mute.")
    private String prod;

    @Column(name = "note", length = 1024, nullable = false)
    @Schema(description = "Additional notes about the alert mute.")
    private String note;

    @Column(name = "cate", length = 128, nullable = false)
    @Schema(description = "The category of the alert mute.")
    private String cate;

    @Column(name = "cluster", length = 128, nullable = false)
    @Schema(description = "The cluster associated with the alert mute.")
    private String cluster;

    @Column(name = "datasource_ids", length = 255, nullable = false)
    @Schema(description = "The IDs of the datasources associated with the alert mute, separated by spaces.")
    private String datasourceIds;

    @Column(name = "tags", length = 4096, nullable = false)
    @Schema(description = "JSON string representing tags, key-value pairs.")
    private String tags;

    @Column(name = "cause", length = 255, nullable = false)
    @Schema(description = "The cause of the alert mute.")
    private String cause;

    @Column(name = "btime", nullable = false)
    @Schema(description = "Begin time of the alert mute.")
    private Long btime;

    @Column(name = "etime", nullable = false)
    @Schema(description = "End time of the alert mute.")
    private Long etime;

    @Column(name = "disabled", nullable = false)
    @Schema(description = "Indicates if the alert mute is disabled.")
    private Boolean disabled;

    @Column(name = "mute_time_type", nullable = false)
    @Schema(description = "Type of the alert mute time.")
    private Integer muteTimeType;

    @Column(name = "periodic_mutes", length = 4096, nullable = false)
    @Schema(description = "Periodic mutes associated with the alert mute.")
    private String periodicMutes;

    @Column(name = "severities", length = 32, nullable = false)
    @Schema(description = "Severities associated with the alert mute.")
    private String severities;
	
}
