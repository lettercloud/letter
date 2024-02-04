package org.letter.console.admin.user.domain.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * SsoConfigVo
 *
 * @author letter
 */
@Getter
@Setter
public class SsoConfigVo {
	private String oidcDisplayName = "";
	private String casDisplayName = "";
	private String oauthDisplayName = "";
}
