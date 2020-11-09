package com.xw;


import com.xw.elastic.domain.StartLog;
import com.xw.elastic.domain.vo.Home;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Receive Application
 *
 * @author aio
 */
@SpringBootApplication
@EnableScheduling //启动定时
@EnableAsync //启用异步
public class Application {
    public Application(){
        Home.createUI();
    }
    public static void main(String[] args) {
        StartLog.log.add("启动Spring服务");
        new SpringApplicationBuilder(Application.class).headless(false).run(args);
    }
}
