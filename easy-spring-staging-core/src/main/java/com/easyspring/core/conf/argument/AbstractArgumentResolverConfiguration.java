package com.easyspring.core.conf.argument;

import com.easyspring.core.argument.SwaggerParamArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 参数解析器加载配置类
 *
 * @author caobaoyu
 * @date 2022/10/1 22:47
 */
public abstract class AbstractArgumentResolverConfiguration implements WebMvcConfigurer {

    // swagger参数解析器
    private SwaggerParamArgumentResolver swaggerParamArgumentResolver;

    /**
     * 非swagger参数，参数解析器列表
     *
     * @return 参数解析器列表
     * @author caobaoyu
     * @date 2022/10/1 22:48
     */
    public abstract List<HandlerMethodArgumentResolver> builderArgumentResolver();

    /**
     * 参数解析器初始化
     *
     * @author caobaoyu
     * @date 2022/10/1 22:49
     */
    @PostConstruct
    private void initArgumentResolver() {
        this.swaggerParamArgumentResolver = new SwaggerParamArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(this.swaggerParamArgumentResolver);
        List<HandlerMethodArgumentResolver> resolverList = builderArgumentResolver();
        if (resolverList != null) {
            for (HandlerMethodArgumentResolver r : resolverList) {
                if (r != null) {
                    resolvers.add(r);
                }
            }
        }
    }
}
