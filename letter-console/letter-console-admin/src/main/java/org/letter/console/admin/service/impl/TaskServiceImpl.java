package org.letter.console.admin.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.letter.console.exception.BadRequestException;
import org.letter.console.admin.domain.task.Task;
import org.letter.console.admin.domain.task.TaskLog;
import org.letter.console.admin.repository.TaskLogRepository;
import org.letter.console.admin.repository.TaskRepository;
import org.letter.console.admin.service.TaskService;
import org.letter.console.admin.service.dto.TaskQueryCriteria;
import org.letter.console.admin.utils.QuartzManage;
import org.letter.console.utils.*;
import org.quartz.CronExpression;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;

/**
 * @author letter
 */
@RequiredArgsConstructor
@Service(value = "quartzTaskService")
public class TaskServiceImpl implements TaskService {

    private final TaskRepository quartzJobRepository;
    private final TaskLogRepository quartzLogRepository;
    private final QuartzManage quartzManage;

    @Override
    public PageResult<Task> queryAll(TaskQueryCriteria criteria, Pageable pageable){
        return PageUtil.toPage(quartzJobRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable));
    }

    @Override
    public PageResult<TaskLog> queryAllLog(TaskQueryCriteria criteria, Pageable pageable){
        return PageUtil.toPage(quartzLogRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable));
    }

    @Override
    public List<Task> queryAll(TaskQueryCriteria criteria) {
        return quartzJobRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder));
    }

    @Override
    public List<TaskLog> queryAllLog(TaskQueryCriteria criteria) {
        return quartzLogRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder));
    }

    @Override
    public Task findById(Long id) {
        Task quartzJob = quartzJobRepository.findById(id).orElseGet(Task::new);
        ValidationUtil.isNull(quartzJob.getId(),"QuartzJob","id",id);
        return quartzJob;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Task resources) {
        if (!CronExpression.isValidExpression(resources.getCronExpression())){
            throw new BadRequestException("cron表达式格式错误");
        }
        resources = quartzJobRepository.save(resources);
        quartzManage.addJob(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Task resources) {

        if(StringUtils.isNotBlank(resources.getSubTask())){
            List<String> tasks = Arrays.asList(resources.getSubTask().split("[,，]"));
            if (tasks.contains(resources.getId().toString())) {
                throw new BadRequestException("子任务中不能添加当前任务ID");
            }
        }
        resources = quartzJobRepository.save(resources);
        quartzManage.updateJobCron(resources);
    }

    @Override
    public void updateIsPause(Task quartzJob) {
        if (quartzJob.getIsPause()) {
            quartzManage.resumeJob(quartzJob);
            quartzJob.setIsPause(false);
        } else {
            quartzManage.pauseJob(quartzJob);
            quartzJob.setIsPause(true);
        }
        quartzJobRepository.save(quartzJob);
    }

    @Override
    public void execution(Task quartzJob) {
        quartzManage.runJobNow(quartzJob);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        for (Long id : ids) {
            Task quartzJob = findById(id);
            quartzManage.deleteJob(quartzJob);
            quartzJobRepository.delete(quartzJob);
        }
    }

    @Async
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void executionSubJob(String[] tasks) throws InterruptedException {
        for (String id : tasks) {
            if (StrUtil.isBlank(id)) {
                // 如果是手动清除子任务id，会出现id为空字符串的问题
                continue;
            }
            Task quartzJob = findById(Long.parseLong(id));
            // 执行任务
            String uuid = IdUtil.simpleUUID();
            quartzJob.setUuid(uuid);
            // 执行任务
            execution(quartzJob);
            // 获取执行状态，如果执行失败则停止后面的子任务执行
//            Boolean result = (Boolean) redisUtils.get(uuid);
//            while (result == null) {
//                // 休眠5秒，再次获取子任务执行情况
//                Thread.sleep(5000);
//                result = (Boolean) redisUtils.get(uuid);
//            }
//            if(!result){
//                redisUtils.del(uuid);
//                break;
//            }
        }
    }

    @Override
    public void download(List<Task> quartzJobs, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (Task quartzJob : quartzJobs) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("任务名称", quartzJob.getTaskName());
            map.put("Bean名称", quartzJob.getBeanName());
            map.put("执行方法", quartzJob.getMethodName());
            map.put("参数", quartzJob.getParams());
            map.put("表达式", quartzJob.getCronExpression());
            map.put("状态", quartzJob.getIsPause() ? "暂停中" : "运行中");
            map.put("描述", quartzJob.getDescription());
            map.put("创建日期", quartzJob.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }

    @Override
    public void downloadLog(List<TaskLog> queryAllLog, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (TaskLog quartzLog : queryAllLog) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("任务名称", quartzLog.getTaskName());
            map.put("Bean名称", quartzLog.getBeanName());
            map.put("执行方法", quartzLog.getMethodName());
            map.put("参数", quartzLog.getParams());
            map.put("表达式", quartzLog.getCronExpression());
            map.put("异常详情", quartzLog.getExceptionDetail());
            map.put("耗时/毫秒", quartzLog.getTime());
            map.put("状态", quartzLog.getIsSuccess() ? "成功" : "失败");
            map.put("创建日期", quartzLog.getCreateTime());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
