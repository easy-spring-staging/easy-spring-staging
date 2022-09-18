/**
 * Copyright(C) 2021 Company:easy-spring-staging Co.
 */
package com.easyspring.core.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * spring 工具类.
 *
 * @author caobaoyu
 * @create 2021-09-14 15:39
 **/
public class SpringUtil {

    public static HttpServletRequest getServletRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
