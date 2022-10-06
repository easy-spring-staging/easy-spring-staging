package com.easyspring.demo.mvc.simple.conf.spring;

import com.easyspring.core.conf.spring.AbstractSpringConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public class SpringConfiguration extends AbstractSpringConfiguration {
    @Override
    public void addCustomResourceHandlers(ResourceHandlerRegistry registry) {

    }

    @Override
    public boolean isCors() {
        return true;
    }
}

