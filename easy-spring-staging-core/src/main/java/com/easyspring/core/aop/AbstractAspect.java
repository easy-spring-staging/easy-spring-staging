/**
 * Copyright(C) 2020 Company:easy-spring-staging Co.
 */
package com.easyspring.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * rest访问处理类 .
 *
 * <p>
 * rest访问切面处理类
 *
 * @author caobaoyu
 * @date 2018/11/1 12:43
 */
public abstract class AbstractAspect {

    /**
     * 切面处理核心处理执行
     *
     * @param joinPoint 连接点
     * @return 目标对象对象返回值
     * @throws
     * @author caobaoyu
     * @date 2018/11/1 12:44
     */
    private Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        // 获取HttpServletRequest
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        // 目标运行前时间戳
        long startTime = System.currentTimeMillis();
        // 前置处理
        preProcess(req);
        result = joinPoint.proceed(joinPoint.getArgs());
        // 目标运行后时间戳
        long endTime = System.currentTimeMillis();
        // 后置处理
        postProcess(startTime, endTime, result, req);
        return result;
    }

    /**
     * 环绕通知
     *
     * @param joinPoint 连接点
     * @return 目标对象对象返回值
     * @throws Throwable
     * @author caobaoyu
     * @date 2018/11/1 12:45
     */
    @Around("pointcut() && (@annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.DeleteMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping) || @annotation(org.springframework.web.bind.annotation.RequestMapping))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        return execute(joinPoint);
    }

    /**
     * 切入点,子类无须实现具体方法逻辑，只需要使用Pointcut注解，标记切入点
     */
    public abstract void pointcut();

    /**
     * 切面前置处理
     *
     * @param req HttpServletRequest
     * @throws Throwable
     * @author caobaoyu
     * @date 2018/11/1 12:50
     */
    public abstract void preProcess(HttpServletRequest req) throws Throwable;

    /**
     * 切面后置处理
     *
     * @param startTime 执行开始时间(时间戳，毫秒)
     * @param endTime   执行结束时间(时间戳，毫秒)
     * @param result    目标对象对象返回值
     * @param req       HttpServletRequest
     * @throws Throwable
     * @author caobaoyu
     * @date 2018/11/1 12:55
     */
    public abstract void postProcess(long startTime, long endTime, Object result, HttpServletRequest req) throws Throwable;
}
