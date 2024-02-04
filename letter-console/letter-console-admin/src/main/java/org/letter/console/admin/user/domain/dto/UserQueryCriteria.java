package org.letter.console.admin.user.domain.dto;

import lombok.Data;
import org.letter.console.annotation.Query;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author letter
 */
@Data
public class UserQueryCriteria implements Serializable {

	@Query
	private Long id;
	
	@Query(blurry = "email,username,nickName")
	private String blurry;

	@Query
	private Boolean enabled;
	

	@Query(type = Query.Type.BETWEEN)
	private List<Timestamp> createTime;
}
