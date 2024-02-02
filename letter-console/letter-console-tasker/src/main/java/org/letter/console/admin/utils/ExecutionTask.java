package org.letter.console.admin.utils;

import org.letter.console.admin.domain.Task;
import org.letter.console.admin.domain.TaskLog;
import org.letter.console.admin.repository.TaskLogRepository;
import org.letter.console.admin.service.TaskService;
import org.letter.console.utils.SpringContextHolder;
import org.letter.console.utils.StringUtils;
import org.letter.console.utils.ThrowableUtil;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.Future;

/**
 * 参考人人开源，<a href="https://gitee.com/renrenio/renren-security">...</a>
 *
 * @author /
 * @date 2019-01-07
 */
@Async
public class ExecutionTask extends QuartzJobBean {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// 此处仅供参考，可根据任务执行情况自定义线程池参数
	private final ThreadPoolTaskExecutor executor = SpringContextHolder.getBean("elAsync");

	@Override
	public void executeInternal(JobExecutionContext context) {
		// 获取任务
		Task quartzJob = (Task) context.getMergedJobDataMap().get(Task.JOB_KEY);
		// 获取spring bean
		TaskLogRepository quartzLogRepository = SpringContextHolder.getBean(TaskLogRepository.class);
		TaskService quartzJobService = SpringContextHolder.getBean(TaskService.class);
		TaskLog log = new TaskLog();
		log.setJobName(quartzJob.getJobName());
		log.setBeanName(quartzJob.getBeanName());
		log.setMethodName(quartzJob.getMethodName());
		log.setParams(quartzJob.getParams());
		long startTime = System.currentTimeMillis();
		log.setCronExpression(quartzJob.getCronExpression());
		try {
			// 执行任务
			QuartzRunnable task = new QuartzRunnable(quartzJob.getBeanName(), quartzJob.getMethodName(), quartzJob.getParams());
			Future<?> future = executor.submit(task);
			future.get();
			long times = System.currentTimeMillis() - startTime;
			log.setTime(times);
			// 任务状态
			log.setIsSuccess(true);
			logger.info("任务执行成功，任务名称：" + quartzJob.getJobName() + ", 执行时间：" + times + "毫秒");
			// 判断是否存在子任务
			if (StringUtils.isNotBlank(quartzJob.getSubTask())) {
				String[] tasks = quartzJob.getSubTask().split("[,，]");
				// 执行子任务
				quartzJobService.executionSubJob(tasks);
			}
		} catch (Exception e) {
			logger.error("任务执行失败，任务名称：" + quartzJob.getJobName());
			long times = System.currentTimeMillis() - startTime;
			log.setTime(times);
			// 任务状态 0：成功 1：失败
			log.setIsSuccess(false);
			log.setExceptionDetail(ThrowableUtil.getStackTrace(e));
			// 任务如果失败了则暂停
			if (quartzJob.getPauseAfterFailure() != null && quartzJob.getPauseAfterFailure()) {
				quartzJob.setIsPause(false);
				//更新状态
				quartzJobService.updateIsPause(quartzJob);
			}
		} finally {
			quartzLogRepository.save(log);
		}
	}

}
