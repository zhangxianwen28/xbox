package com.xw.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ContextClosed implements ApplicationListener<ContextClosedEvent> {
    /**
     * 当容器关闭时发布,即调用close方法, 关闭意味着所有的单例bean都已被销毁.关闭的容器不能被重启或refresh
     */
    @Override
    public void onApplicationEvent(ContextClosedEvent applicationEvent) {
        log.info("=====================ContextClosed=====================");
    }
}
