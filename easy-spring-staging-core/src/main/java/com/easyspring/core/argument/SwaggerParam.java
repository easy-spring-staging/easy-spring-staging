package com.easyspring.core.argument;


import java.lang.annotation.*;

/**
 * Swagger参数解析注解
 *
 * @author caobaoyu
 * @date 2022/10/1 22:46
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SwaggerParam {
}
