package com.easyspring.core.argument;

import com.easyspring.core.sercurity.AuthorizationUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 解析用户信息抽象类
 *
 * @author caobaoyu
 * @date 2022/10/1 22:40
 */
public abstract class AbstractUserParamArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserParam.class);
    }

    @Override
    public AuthorizationUser resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        return parserUser(parameter, webRequest);
    }

    /**
     * 解析用户信息的抽象方法
     *
     * @param parameter  方法参数
     * @param webRequest request
     * @return 无
     * @author caobaoyu
     * @date 2022/10/1 22:40
     */
    public abstract AuthorizationUser parserUser(MethodParameter parameter, NativeWebRequest webRequest);
}
