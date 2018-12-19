package com.originaltek.botgo.monitor.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MonitorIntersepter implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        before();
        Object proceed = methodInvocation.proceed();
        after();
        return proceed;
    }

    private void after() {
    }

    private void before() {
    }
}
