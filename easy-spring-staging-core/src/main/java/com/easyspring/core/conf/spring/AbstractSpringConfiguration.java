/**
 * Copyright(C) 2021 Company:easy-spring-staging Co.
 */
package com.easyspring.core.conf.spring;

import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * spring抽象配置类.
 *
 * @author caobaoyu
 * @create 2021-09-10 15:07
 **/
public abstract class AbstractSpringConfiguration implements WebMvcConfigurer {

    /**
     * 增加自定义资源处理
     *
     * @param registry ResourceHandlerRegistry
     * @author caobaoyu
     * @date 2021/9/10 15:16
     */

    public abstract void addCustomResourceHandlers(ResourceHandlerRegistry registry);

    /**
     * 获取是否开启跨域共享资源状态
     *
     * @return 开启跨域共享资源状态, true:开启；false:不开启
     */
    public abstract boolean isCors();

    /**
     * 跨域共享资源登记信息构建
     *
     * @param registry 跨域注册器
     * @author caobaoyu
     * @create 2021-09-10 15:17
     */
    private void addCors(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .exposedHeaders(HttpHeaders.SET_COOKIE)
                .maxAge(3600L);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        addCustomResourceHandlers(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (isCors()) {
            addCors(registry);
        }
    }

}
