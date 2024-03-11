package org.letter.console.admin.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.letter.console.admin.config.properties.UserRsaProperties;
import org.letter.console.admin.user.domain.vo.RsaConfigVo;
import org.letter.console.admin.user.domain.vo.ShowCaptchaVo;
import org.letter.console.admin.user.domain.vo.SsoConfigVo;
import org.letter.console.admin.user.service.AuthConfigService;
import org.springframework.stereotype.Component;

/**
 * AuthConfigService
 *
 * @author wuhao
 */
@Component
@RequiredArgsConstructor
public class AutoConfigServiceImpl implements AuthConfigService {
	private final UserRsaProperties rsaProperties;

	@Override
	public SsoConfigVo getSsoConfig() {
		return new SsoConfigVo();
	}

	@Override
	public ShowCaptchaVo getShowCaptcha() {
		return new ShowCaptchaVo();
	}

	@Override
	public RsaConfigVo getRsaConfig() {
		RsaConfigVo rsaConfigVo = new RsaConfigVo();
		rsaConfigVo.setOpenRSA(rsaProperties.isOpen());
		rsaConfigVo.setRsaPublicKey(rsaProperties.getPublicKey());
		return new RsaConfigVo();
	}

	@Override
	public String getRsaPublicKey() {
		return rsaProperties.getPublicKey();
	}

	@Override
	public String getRsaPrivateKey() {
		return rsaProperties.getPrivateKey();
	}

	@Override
	public boolean isOpenRsa() {
		return rsaProperties.isOpen();
	}
}
