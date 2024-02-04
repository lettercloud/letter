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
package org.letter.console.tasker.domain.task;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author Zheng Jie
 * @date 2019-01-07
 */
@Entity
@Data
@Table(name = "sys_quartz_log")
public class TaskLog implements Serializable {

    @Id
    @Column(name = "log_id")
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    private String jobName;

    
    private String beanName;

    
    private String methodName;

    
    private String params;

    
    private String cronExpression;

    
    private Boolean isSuccess;

    
    private String exceptionDetail;

    
    private Long time;

    @CreationTimestamp
    
    private Timestamp createTime;
}
