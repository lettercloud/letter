package org.letter.console.admin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * AdminConfigBeanConfiguration
 * 自动化 配置
 * 
 * @author letter
 **/
@Configuration
public class AdminConfigBeanConfiguration {
	@Bean
	public PasswordEncoder passwordEncoder() {
		// 密码加密方式
		return new BCryptPasswordEncoder();
	}
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
