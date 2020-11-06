package com.xw.listener;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;

@Slf4j
@Component
public class RequestHandled implements ApplicationListener<RequestHandledEvent> {
    /**
     * 这只在使用spring的DispatcherServlet时有效,当一个请求被处理完成时发布
     * @param applicationEvent
     */
    @Override
    public void onApplicationEvent(RequestHandledEvent applicationEvent) {
        log.info("=====================RequestHandled=====================");
    }
}
    





