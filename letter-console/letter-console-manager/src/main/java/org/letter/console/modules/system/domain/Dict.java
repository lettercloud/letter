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
package org.letter.console.modules.system.domain;


import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

/**
* @author Zheng Jie
* @date 2019-04-10
*/
@Entity
@Getter
@Setter
@Table(name="sys_dict")
public class Dict extends BaseEntity implements Serializable {

    @Id
    @Column(name = "dict_id")
    @NotNull(groups = Update.class)
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "dict",cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    private List<DictDetail> dictDetails;

    @NotBlank
    
    private String name;

    
    private String description;
}