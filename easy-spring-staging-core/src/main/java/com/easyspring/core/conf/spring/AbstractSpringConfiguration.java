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

    public abstract boolean isSwagger();

    public abstract boolean isCors();

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (isSwagger()) {
            addSwagger(registry);
        }
        addCustomResourceHandlers(registry);

    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (isCors()) {
            addCors(registry);
        }
    }

    private void addSwagger(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/swagger-resources/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger-resources/");

        registry.addResourceHandler("/swagger/**")
                .addResourceLocations("classpath:/META-INF/resources/swagger*");

        registry.addResourceHandler("/v2/api-docs/**")
                .addResourceLocations("classpath:/META-INF/resources/v2/api-docs/");
    }

    private void addCors(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .exposedHeaders(HttpHeaders.SET_COOKIE)
                .maxAge(3600L);
    }
}
