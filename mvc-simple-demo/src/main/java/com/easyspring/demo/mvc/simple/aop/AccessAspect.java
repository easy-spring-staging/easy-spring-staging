package com.easyspring.demo.mvc.simple.aop;

import com.easyspring.core.aop.AbstractAspect;
import com.easyspring.core.sercurity.AuthorizationUser;
import com.easyspring.core.sercurity.DefaultAuthorizationUser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Component
@Aspect
public class AccessAspect extends AbstractAspect {


    @Around("url() && (@annotation(org.springframework.web.bind.annotation.GetMapping) || @annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.DeleteMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping))")
    @Override
    public Object restAspect(ProceedingJoinPoint joinPoint) throws Throwable {
        return restAround(joinPoint);
    }

    @Override
    public void restEndHandler(long startTimeStamp, long endTimeStamp, Object result, HttpServletRequest request) throws Throwable {

    }

    @Override
    public void restStartHandler(HttpServletRequest request) throws Throwable {
        String uri = request.getRequestURI();
        log.info("访问路径:" + uri);
        DefaultAuthorizationUser user = new DefaultAuthorizationUser();
        request.setAttribute(AuthorizationUser.DEFAULT_USER_KEY_NAME, user);

    }


    @Pointcut("execution(* com.easyspring.demo.mvc.simple.modules.*.controller..*.*(..))")
    public void url() {
    }
}

