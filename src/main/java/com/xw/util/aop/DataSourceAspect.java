package com.xw.util.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class DataSourceAspect {
    public static  ConcurrentHashMap<String, Instant> concurrentHashMap = new ConcurrentHashMap<>();

    @Bean
    public Advisor dataSourceAdvisor() {
        Pointcut pointcut = new AnnotationMatchingPointcut(Dao.class, true);
        Advice advice = new MethodAroundAdvice2();
        return new DefaultPointcutAdvisor(pointcut, advice);
    }

    private static class MethodAroundAdvice implements MethodBeforeAdvice, AfterReturningAdvice {
        @Override
        public void before(Method method, Object[] args, Object target) throws Throwable {
           log.info("==> {} before ", method.getName());
        }
        @Override
        public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
            if(returnValue instanceof List){
                List list = (List)returnValue;
                log.info("<== {} size{}", method.getName() ,list.size());
            }else {
            log.info("<== {}" , method.getName());
            }
        }
    }

    private static class MethodAroundAdvice2 implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            System.out.println("Before: invocation=[" + invocation + "]");  //(1)
            Object rval = invocation.proceed();
            System.out.println("Invocation returned");  //(2)
            return rval;
        }
    }

}
