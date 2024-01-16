package org.letter.console.config.thread;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 线程池配置属性类
 * @author letter
 */
@Data
@Component
@ConfigurationProperties(prefix = "tool.task")
public class AsyncTaskProperties {

    private int corePoolSize = 10;

	private int maxPoolSize = 30;

	private int keepAliveSeconds = 60;

	private int queueCapacity = 50;

}
