package com.xw;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
