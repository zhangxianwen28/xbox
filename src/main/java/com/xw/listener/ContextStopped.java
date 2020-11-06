package com.xw.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ContextStopped implements ApplicationListener<ContextStoppedEvent> {
    /**
     * 当容器停止时发布,即调用stop()方法, 即所有的Lifecycle bean都已显式接收到了stop信号 , 关闭的容器可以通过start()方法重启
     * @param applicationEvent
     */
    @Override
    public void onApplicationEvent(ContextStoppedEvent applicationEvent) {
        log.info("=====================ContextStopped=====================");
    }
}
    





