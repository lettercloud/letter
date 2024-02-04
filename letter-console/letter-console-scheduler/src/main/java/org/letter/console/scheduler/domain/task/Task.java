package org.letter.console.scheduler.domain.task;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
@Getter
@Setter
@Entity
@Table(name = "console_task")
public class Task extends BaseEntity implements Serializable {

    public static final String TASK_KEY = "TASK_KEY";

	@Id
	@Column(name = "task_id")
	@NotNull(groups = {Update.class})
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Transient
	@Schema(description = "用于子任务唯一标识", hidden = true)
	private String uuid;


	@Schema(description = "分组ID")
	private long groupId;
	
	@Schema(description = "定时器名称")
	private String taskName;

	@NotBlank
	@Schema(description = "数据源名称")
	private String sourceIds;
	
	@Schema(description = "分组名称")
	private String groupName;

	@NotBlank
	@Schema(description = "Bean名称")
	private String beanName;

	@Schema(description = "方法名称")
	private String methodName = "run";

	@Schema(description = "运行参数")
	private String params;

	@Schema(description = "cron表达式")
	private String cronExpression;

	@Schema(description = "持续时间")
	private long checkTime;

	@Schema(description = "持续时间")
	private long durationTime;

	@Schema(description = "留观时长")
	private long viewTime;

	@Schema(description = "发送时间间隔")
	private long sendIntervalTime;

	@Schema(description = "最大发送次数")
	private long maxSendCount;

	@Schema(description = "是否发送恢复通知")
	private boolean sendRecovery;

	@Schema(description = "开始时间")
	private long startTime;

	@Schema(description = "结束时间")
	private long endTime;

	@Schema(description = "任务级别")
	private long level;

	@Schema(description = "状态，暂时或启动")
	private Boolean isPause = false;

	@Schema(description = "回调地址")
	private String tag;

	@Schema(description = "回调地址")
	private String callBacks;

	@Schema(description = "失败后暂停")
	private Boolean pauseAfterFailure;

	@NotBlank
	@Schema(description = "备注")
	private String description;
}