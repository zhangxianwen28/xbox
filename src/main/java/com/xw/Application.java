package com.xw;


import com.xw.util.StartLog;
import com.xw.swing.elastic.Home;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
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
