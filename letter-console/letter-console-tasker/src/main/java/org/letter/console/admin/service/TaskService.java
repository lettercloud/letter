/*
 *  Copyright 2019-2020 Zheng Jie
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.letter.console.admin.service;

import org.letter.console.admin.domain.Task;
import org.letter.console.admin.domain.TaskLog;
import org.letter.console.admin.service.dto.TaskQueryCriteria;
import org.letter.console.utils.PageResult;
import org.springframework.data.domain.Pageable;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * @author letter
 * @date 2019-01-07
 */
public interface TaskService {

    /**
     * 分页查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    PageResult<Task> queryAll(TaskQueryCriteria criteria, Pageable pageable);

    /**
     * 查询全部
     * @param criteria 条件
     * @return /
     */
    List<Task> queryAll(TaskQueryCriteria criteria);

    /**
     * 分页查询日志
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    PageResult<TaskLog> queryAllLog(TaskQueryCriteria criteria, Pageable pageable);

    /**
     * 查询全部
     * @param criteria 条件
     * @return /
     */
    List<TaskLog> queryAllLog(TaskQueryCriteria criteria);

    /**
     * 创建
     * @param resources /
     */
    void create(Task resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(Task resources);

    /**
     * 删除任务
     * @param ids /
     */
    void delete(Set<Long> ids);

    /**
     * 根据ID查询
     * @param id ID
     * @return /
     */
    Task findById(Long id);

    /**
     * 更改定时任务状态
     * @param quartzJob /
     */
    void updateIsPause(Task quartzJob);

    /**
     * 立即执行定时任务
     * @param quartzJob /
     */
    void execution(Task quartzJob);

    /**
     * 导出定时任务
     * @param queryAll 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<Task> queryAll, HttpServletResponse response) throws IOException;

    /**
     * 导出定时任务日志
     * @param queryAllLog 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void downloadLog(List<TaskLog> queryAllLog, HttpServletResponse response) throws IOException;

    /**
     * 执行子任务
     * @param tasks /
     * @throws InterruptedException /
     */
    void executionSubJob(String[] tasks) throws InterruptedException;
}
