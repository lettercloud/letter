package org.letter.console.modules.mnt.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.letter.console.base.BaseEntity;
import java.io.Serializable;

/**
* @author letter
*/
@Entity
@Getter
@Setter
@Table(name="mnt_app")
public class App extends BaseEntity implements Serializable {

    @Id
	@Column(name = "app_id")
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	
    private String name;

	
	private int port;

	
	private String uploadPath;

	
	private String deployPath;

	
	private String backupPath;

	
	private String startScript;

	
	private String deployScript;

    public void copy(App source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
