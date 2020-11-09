package com.xw.listener;


import com.xw.elastic.domain.StartLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ContextRefreshed implements ApplicationListener<ContextRefreshedEvent> {
    /**
     * 当容器被实例化或refreshed时发布.如调用refresh()方法, 此处的实例化是指所有的bean都已被加载,后置处理器都被激活,所有单例bean都已被实例化, 所有的容器对象都已准备好可使用. 如果容器支持热重载,则refresh可以被触发多次(XmlWebApplicatonContext支持热刷新,而GenericApplicationContext则不支持)
     * @param applicationEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent applicationEvent) {
        log.info("=====================ContextRefreshed=====================");
        StartLog.log.add("Spring上下文刷新");
    }
}




