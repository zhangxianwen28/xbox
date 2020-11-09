package com.xw.listener.application;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationStarting implements ApplicationListener<org.springframework.boot.context.event.ApplicationStartingEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationEvent) {
        log.info("=====================ApplicationStartingEvent=====================");
    }
}
