package org.letter.console.tasker.domain.alert;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import java.io.Serializable;

/**
 * AlertRule
 *
 * @author letter
 */
@Getter
@Setter
@Entity
@Table(name = "alert_rule")
public class AlertRule  extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	@Schema(description = "The unique identifier of the alert rule.")
	private Long id;

	@Column(name = "group_id", nullable = false)
	@Schema(description = "The ID of the business group associated with the alert rule.")
	private Long groupId;

	@Column(name = "cate", length = 128, nullable = false)
	@Schema(description = "The category of the alert rule.")
	private String cate;

	@Column(name = "datasource_ids", length = 255, nullable = false)
	@Schema(description = "The IDs of the datasources associated with the alert rule, separated by spaces.")
	private String datasourceIds;

	@Column(name = "cluster", length = 128, nullable = false)
	@Schema(description = "The cluster associated with the alert rule.")
	private String cluster;

	@Column(name = "name", length = 255, nullable = false)
	@Schema(description = "The name of the alert rule.")
	private String name;

	@Column(name = "note", length = 1024, nullable = false)
	@Schema(description = "Additional notes about the alert rule.")
	private String note;

	@Column(name = "prod", length = 255, nullable = false)
	@Schema(description = "The production environment associated with the alert rule.")
	private String prod;

	@Column(name = "algorithm", length = 255, nullable = false)
	@Schema(description = "The algorithm used for alerting.")
	private String algorithm;

	@Column(name = "algo_params", length = 255)
	@Schema(description = "The parameters for the algorithm.")
	private String algoParams;

	@Column(name = "delay", nullable = false)
	@Schema(description = "The delay before triggering the alert.")
	private Integer delay;

	@Column(name = "severity", nullable = false)
	@Schema(description = "The severity level of the alert.")
	private Integer severity;

	@Column(name = "disabled", nullable = false)
	@Schema(description = "Indicates if the alert rule is disabled.")
	private Boolean disabled;

	@Column(name = "prom_for_duration", nullable = false)
	@Schema(description = "Duration of the Prometheus for, in seconds.")
	private Integer promForDuration;

	@Column(name = "rule_config", columnDefinition = "TEXT", nullable = false)
	@Schema(description = "Configuration of the alert rule.")
	private String ruleConfig;

	@Column(name = "prom_ql", columnDefinition = "TEXT", nullable = false)
	@Schema(description = "PromQL query associated with the alert rule.")
	private String promQl;

	@Column(name = "prom_eval_interval", nullable = false)
	@Schema(description = "Evaluation interval for the PromQL query.")
	private Integer promEvalInterval;

	@Column(name = "enable_stime", length = 255, nullable = false)
	@Schema(description = "Start time for enabling the alert rule.")
	private String enableStime;

	@Column(name = "enable_etime", length = 255, nullable = false)
	@Schema(description = "End time for enabling the alert rule.")
	private String enableEtime;

	@Column(name = "enable_days_of_week", length = 255, nullable = false)
	@Schema(description = "Days of the week for enabling the alert rule, separated by spaces.")
	private String enableDaysOfWeek;

	@Column(name = "enable_in_bg", nullable = false)
	@Schema(description = "Indicates if the alert rule is enabled only for this business group.")
	private Boolean enableInBg;

	@Column(name = "notify_recovered", nullable = false)
	@Schema(description = "Indicates whether to notify when the alert recovers.")
	private Boolean notifyRecovered;

	@Column(name = "notify_channels", length = 255, nullable = false)
	@Schema(description = "Notification channels associated with the alert rule, separated by spaces.")
	private String notifyChannels;

	@Column(name = "notify_groups", length = 255, nullable = false)
	@Schema(description = "Notification groups associated with the alert rule, separated by spaces.")
	private String notifyGroups;

	@Column(name = "notify_repeat_step", nullable = false)
	@Schema(description = "Repeat step for notification, in minutes.")
	private Integer notifyRepeatStep;

	@Column(name = "notify_max_number", nullable = false)
	@Schema(description = "Maximum number of notifications.")
	private Integer notifyMaxNumber;

	@Column(name = "recover_duration", nullable = false)
	@Schema(description = "Recovery duration, in seconds.")
	private Integer recoverDuration;

	@Column(name = "callbacks", length = 1024, nullable = false)
	@Schema(description = "Callback URLs associated with the alert rule, separated by spaces.")
	private String callbacks;

	@Column(name = "runbook_url", length = 255)
	@Schema(description = "URL to the runbook associated with the alert rule.")
	private String runbookUrl;

	@Column(name = "append_tags", length = 255, nullable = false)
	@Schema(description = "Additional tags associated with the alert rule, separated by spaces.")
	private String appendTags;

	@Column(name = "annotations", columnDefinition = "TEXT", nullable = false)
	@Schema(description = "Annotations associated with the alert rule.")
	private String annotations;

	@Column(name = "extra_config", columnDefinition = "TEXT", nullable = false)
	@Schema(description = "Extra configuration associated with the alert rule.")
	private String extraConfig;
}
