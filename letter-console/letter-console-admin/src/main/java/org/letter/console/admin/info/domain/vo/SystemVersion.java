package org.letter.console.admin.info.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * SystemVersion
 * 
 * @author wuhao
 */
@Setter
@Getter
public class SystemVersion {
	@JsonProperty("github_version")
	private String githubVersion = "";
	@JsonProperty("version")
	private String version = "";
}
