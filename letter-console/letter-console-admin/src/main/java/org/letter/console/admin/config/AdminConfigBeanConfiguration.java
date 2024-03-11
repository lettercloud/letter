package org.letter.console.admin.config;

import org.letter.console.admin.config.properties.SecurityProperties;
import org.letter.console.admin.config.properties.UserLoginProperties;
import org.letter.console.admin.config.properties.UserRsaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AdminConfigBeanConfiguration
 * 自动化 配置
 *
 * @author letter
 **/
@Configuration
public class AdminConfigBeanConfiguration {
	@Bean
	@ConfigurationProperties(prefix = "ursa")
	public UserRsaProperties userRsaProperties() {
		return new UserRsaProperties();
	}

	@Bean
	@ConfigurationProperties(prefix = "login")
	public UserLoginProperties loginProperties() {
		return new UserLoginProperties();
	}

	@Bean
	@ConfigurationProperties(prefix = "jwt")
	public SecurityProperties securityProperties() {
		return new SecurityProperties();
	}
}
