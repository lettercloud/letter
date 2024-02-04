package org.letter.console.admin.user.service;

import org.letter.console.admin.user.domain.vo.RsaConfigVo;
import org.letter.console.admin.user.domain.vo.ShowCaptchaVo;
import org.letter.console.admin.user.domain.vo.SsoConfigVo;

/**
 * @author wuhao
 */
public interface AuthConfigService {
	/**
	 * getConfig
	 * 
	 * @return
	 */
	SsoConfigVo getSsoConfig();

	/**
	 * getShowCaptcha
	 *
	 * @return
	 */
	ShowCaptchaVo getShowCaptcha();

	/**
	 * getRsaConfig
	 *
	 * @return
	 */
	RsaConfigVo getRsaConfig();
}
