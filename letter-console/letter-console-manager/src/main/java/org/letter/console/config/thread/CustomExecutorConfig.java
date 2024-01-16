package org.letter.console.config.thread;

import lombok.RequiredArgsConstructor;
import org.letter.console.config.RsaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 创建自定义的线程池
 * @author letter
 **/
@RequiredArgsConstructor
@Configuration
public class CustomExecutorConfig {
	private final AsyncTaskProperties taskProperties;

    /**
     * 自定义线程池，用法 @Async
     * @return Executor
     */
    @Bean
    @Primary
    public Executor elAsync() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(taskProperties.getCorePoolSize());
        executor.setMaxPoolSize(taskProperties.getMaxPoolSize());
        executor.setQueueCapacity(taskProperties.getQueueCapacity());
        executor.setThreadNamePrefix("el-async-");
        executor.setKeepAliveSeconds(taskProperties.getKeepAliveSeconds());
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    /**
     * 自定义线程池，用法 @Async("otherAsync")
     * @return Executor
     */
    @Bean
    public Executor otherAsync() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(15);
        executor.setQueueCapacity(50);
        executor.setKeepAliveSeconds(taskProperties.getKeepAliveSeconds());
        executor.setThreadNamePrefix("el-task-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
