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
//package org.letter.console.scheduler.repository;
//
//import org.letter.console.admin.domain.task.Task;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import java.util.List;
//
///**
// * @author Zheng Jie
// * @date 2019-01-07
// */
//public interface TaskRepository extends JpaRepository<Task,Long>, JpaSpecificationExecutor<Task> {
//
//    /**
//     * 查询启用的任务
//     * @return List
//     */
//    List<Task> findByIsPauseIsFalse();
//}
