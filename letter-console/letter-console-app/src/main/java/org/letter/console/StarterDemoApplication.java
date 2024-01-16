package org.letter.console;

import me.zhyd.oauth.cache.AuthCacheConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ControllerAdvice
@EnableCaching
@SpringBootApplication
public class StarterDemoApplication implements ApplicationRunner {

    @Value("${server.port}")
    public int port;


    public static void main(String[] args) {
        AuthCacheConfig.timeout = 30 * 60 * 1000;
        SpringApplication.run(StarterDemoApplication.class, args);
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object handle(Throwable e) {
        e.printStackTrace();
        return e.getMessage();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("已启动： http://localhost:" + port);
    }
}
