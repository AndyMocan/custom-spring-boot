package com.originaltek.botgo.monitor.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MonitorPointcutAdvisor extends AbstractPointcutAdvisor {

    private Pointcut pointcut;

    private Advice advice;

    @PostConstruct
    public void init() {
        log.info("init MonitorPointcutAdvisor start");
        this.pointcut = new AnnotationMatchingPointcut(Monitor.class);
        this.advice = new MonitorIntersepter();
        log.info("init MonitorPointcutAdvisor end");
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

}
