package org.letter.cloud.spring;

import org.letter.cloud.spring.context.SpringContextUtil;
import org.letter.cloud.spring.filter.MetricsFilter;
import org.letter.cloud.spring.log.LoggerConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Arrays;

/**
 * CommonAutoConfiguration
 *
 * @author wuhao
 */
@Configuration
public class CommonAutoConfiguration {

	@Bean
	public SpringContextUtil getSpringContextUtil() {
		return new SpringContextUtil();
	}

	@Bean
	public LoggerConfiguration geLoggerConfiguration(){
		return new LoggerConfiguration();
	}

	@Bean
	public FilterRegistrationBean<Filter> getMetricsFilter() {
		FilterRegistrationBean<Filter> filterRegistration = new FilterRegistrationBean();
		MetricsFilter metricsFilter = new MetricsFilter();
		filterRegistration.setFilter(metricsFilter);
		filterRegistration.setUrlPatterns(Arrays.asList("/*"));
		filterRegistration.setOrder(1);
		return filterRegistration;
	}
}
