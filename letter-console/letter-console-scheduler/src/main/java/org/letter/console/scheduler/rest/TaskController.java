///*
// *  Copyright 2019-2020 Zheng Jie
// *
// *  Licensed under the Apache License, Version 2.0 (the "License");
// *  you may not use this file except in compliance with the License.
// *  You may obtain a copy of the License at
// *
// *  http://www.apache.org/licenses/LICENSE-2.0
// *
// *  Unless required by applicable law or agreed to in writing, software
// *  distributed under the License is distributed on an "AS IS" BASIS,
// *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// *  See the License for the specific language governing permissions and
// *  limitations under the License.
// */
//package org.letter.console.scheduler.rest;
//
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.letter.console.annotation.Log;
//import org.letter.console.exception.BadRequestException;
//import org.letter.console.admin.domain.task.Task;
//import org.letter.console.admin.domain.task.TaskLog;
//import org.letter.console.scheduler.service.TaskService;
//import org.letter.console.scheduler.service.dto.TaskQueryCriteria;
//import org.letter.console.utils.PageResult;
//import org.letter.console.utils.SpringContextHolder;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.Set;
//
///**
// * @author Zheng Jie
// * @date 2019-01-07
// */
//@Slf4j
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api/tasks")
//@Tag(name = "系统:任务管理")
//public class TaskController {
//
//    private static final String ENTITY_NAME = "tasks";
//    private final TaskService quartzJobService;
//
//    @Operation(summary = "查询定时任务")
//    @GetMapping
//    @PreAuthorize("@el.check('tasks:list')")
//    public ResponseEntity<PageResult<Task>> queryQuartzJob(TaskQueryCriteria criteria, Pageable pageable){
//        return new ResponseEntity<>(quartzJobService.queryAll(criteria,pageable), HttpStatus.OK);
//    }
//
//    @Operation(summary = "导出任务数据")
//    @GetMapping(value = "/download")
//    @PreAuthorize("@el.check('tasks:list')")
//    public void exportQuartzJob(HttpServletResponse response, TaskQueryCriteria criteria) throws IOException {
//        quartzJobService.download(quartzJobService.queryAll(criteria), response);
//    }
//
//    @Operation(summary = "导出日志数据")
//    @GetMapping(value = "/logs/download")
//    @PreAuthorize("@el.check('tasks:list')")
//    public void exportQuartzJobLog(HttpServletResponse response, TaskQueryCriteria criteria) throws IOException {
//        quartzJobService.downloadLog(quartzJobService.queryAllLog(criteria), response);
//    }
//
//    @Operation(summary = "查询任务执行日志")
//    @GetMapping(value = "/logs")
//    @PreAuthorize("@el.check('tasks:list')")
//    public ResponseEntity<PageResult<TaskLog>> queryQuartzJobLog(TaskQueryCriteria criteria, Pageable pageable){
//        return new ResponseEntity<>(quartzJobService.queryAllLog(criteria,pageable), HttpStatus.OK);
//    }
//
//    @Log("新增定时任务")
//    @Operation(summary = "新增定时任务")
//    @PostMapping
//    @PreAuthorize("@el.check('tasks:add')")
//    public ResponseEntity<Object> createQuartzJob(@Validated @RequestBody Task resources){
//        if (resources.getId() != null) {
//            throw new BadRequestException("A new "+ ENTITY_NAME +" cannot already have an ID");
//        }
//        // 验证Bean是不是合法的，合法的定时任务 Bean 需要用 @Service 定义
//        checkBean(resources.getBeanName());
//        quartzJobService.create(resources);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @Log("修改定时任务")
//    @Operation(summary = "修改定时任务")
//    @PutMapping
//    @PreAuthorize("@el.check('tasks:edit')")
//    public ResponseEntity<Object> updateQuartzJob(@Validated(Task.Update.class) @RequestBody Task resources){
//        // 验证Bean是不是合法的，合法的定时任务 Bean 需要用 @Service 定义
//        checkBean(resources.getBeanName());
//        quartzJobService.update(resources);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @Log("更改定时任务状态")
//    @Operation(summary = "更改定时任务状态")
//    @PutMapping(value = "/{id}")
//    @PreAuthorize("@el.check('tasks:edit')")
//    public ResponseEntity<Object> updateQuartzJobStatus(@PathVariable Long id){
//        quartzJobService.updateIsPause(quartzJobService.findById(id));
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @Log("执行定时任务")
//    @Operation(summary = "执行定时任务")
//    @PutMapping(value = "/exec/{id}")
//    @PreAuthorize("@el.check('tasks:edit')")
//    public ResponseEntity<Object> executionQuartzJob(@PathVariable Long id){
//        quartzJobService.execution(quartzJobService.findById(id));
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @Log("删除定时任务")
//    @Operation(summary = "删除定时任务")
//    @DeleteMapping
//    @PreAuthorize("@el.check('tasks:del')")
//    public ResponseEntity<Object> deleteQuartzJob(@RequestBody Set<Long> ids){
//        quartzJobService.delete(ids);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    private void checkBean(String beanName){
//        // 避免调用攻击者可以从SpringContextHolder获得控制jdbcTemplate类
//        // 并使用getDeclaredMethod调用jdbcTemplate的queryForMap函数，执行任意sql命令。
//        if(!SpringContextHolder.getAllServiceBeanName().contains(beanName)){
//            throw new BadRequestException("非法的 Bean，请重新输入！");
//        }
//    }
//}
