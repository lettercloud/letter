package org.letter.console.admin;

import org.letter.console.annotation.rest.AnonymousGetMapping;
import org.letter.console.utils.SpringContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * AdminRun
 *
 * @author letter
 */
@EnableAsync
@RestController
@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class AdminRun {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(AdminRun.class);
		springApplication.addListeners(new ApplicationPidFileWriter());
		springApplication.run(args);
	}

	@Bean
	public SpringContextHolder springContextHolder() {
		return new SpringContextHolder();
	}

	/**
	 * 访问首页提示
	 *
	 * @return /
	 */
	@AnonymousGetMapping("/")
	public String index() {
		return "Backend service started successfully";
	}
}
