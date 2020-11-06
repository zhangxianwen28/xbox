package com.xw.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ContextStarted implements ApplicationListener<ContextStartedEvent> {
    /**
     * 当容器启动时发布,即调用start()方法, 已启用意味着所有的Lifecycle bean都已显式接收到了start信号
     *
     * @param applicationEvent
     */
    @Override
    public void onApplicationEvent(ContextStartedEvent applicationEvent) {
        log.info("=====================ContextStarted=====================");
    }
}




