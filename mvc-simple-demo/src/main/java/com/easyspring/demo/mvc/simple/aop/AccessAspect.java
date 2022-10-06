package com.easyspring.demo.mvc.simple.aop;

import com.easyspring.core.aop.AbstractAspect;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Component
@Aspect
public class AccessAspect extends AbstractAspect {

    @Override
    public void postProcess(long startTime, long endTime, Object result, HttpServletRequest request) throws Throwable {
    }

    @Override
    public void preProcess(HttpServletRequest req) throws Throwable {
        String uri = req.getRequestURI();
        log.info("访问路径:" + uri);
    }


    @Pointcut("execution(* com.easyspring.demo.mvc.simple.modules.*.controller..*.*(..))")
    public void pointcut() {
    }
}

