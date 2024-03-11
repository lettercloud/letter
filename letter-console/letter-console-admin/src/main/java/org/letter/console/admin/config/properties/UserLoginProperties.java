package org.letter.console.admin.config.properties;

import lombok.Data;
import org.letter.console.admin.user.domain.entity.LoginCode;

/**
 * UserLoginProperties
 * 用户登录配置
 *
 * @author letter
 **/
@Data
public class UserLoginProperties {

	/**
	 * 账号单用户 登录
	 */
	private boolean singleLogin = false;

	private LoginCode loginCode = new LoginCode();

	private long cacheIdleTime = 21600L;

	public static final String cacheKey = "user_login_cache:";

	public boolean isSingleLogin() {
		return singleLogin;
	}

}
