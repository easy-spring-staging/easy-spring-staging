/**
 * Copyright(C) 2020 Company:easy-spring-staging Co.
 */
package com.easyspring.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * rest访问处理类 .
 *
 * <p>
 * rest访问处理类
 *
 * @author caobaoyu
 * @date 2018/11/1 12:43
 */
public abstract class AbstractAspect {

    protected Object restAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        long startTimeStamp;
        long endTimeStamp;
        restStartHandler(request);
        startTimeStamp = System.currentTimeMillis();
        result = joinPoint.proceed(joinPoint.getArgs());
        endTimeStamp = System.currentTimeMillis();
        restEndHandler(startTimeStamp, endTimeStamp, result, request);
        return result;
    }

    public abstract Object restAspect(ProceedingJoinPoint joinPoint) throws Throwable;

    public abstract void restEndHandler(long startTimeStamp, long endTimeStamp, Object result,
                                        HttpServletRequest request) throws Throwable;

    public abstract void restStartHandler(HttpServletRequest request) throws Throwable;
}
