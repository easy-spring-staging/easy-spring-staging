/**
 * Copyright(C) 2018 Company:easy-spring-staging Co.
 */

package com.easyspring.core.exception;


import com.easyspring.core.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 异常捕获拦截器  .
 *
 * <p>
 * 功能详细描述
 *
 * @author caobaoyu
 * @date 2018/10/29 14:11
 */
public abstract class AbstractExceptionAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractExceptionAdvice.class);

    /**
     * 异常映射Map
     */
    protected Map<String, Integer> exceptionMapping;
    protected Map<Integer,String> exceptionEnumMapping;



    public AbstractExceptionAdvice() {
        exceptionMapping = new HashMap<>();
        exceptionEnumMapping = new HashMap<>();
    }


    /**
     * 统一的异常处理展示信息处理.
     *
     * <p>
     * 统一的异常处理展示信息处理
     *
     * @param request   请求
     * @param exception 异常
     * @return ResponseModel<String> 异常捕获信息
     * @author caobaoyu
     * @date 2018/10/29 14:45
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseModel<String> handler(HttpServletRequest request, HttpServletResponse response, Exception exception) throws Exception {
        ResponseModel<String> responseModel = null;
        if (exception != null) {
            Integer code = ExceptionEnum.ERROR_INTERNAL_SERVER.getCode();
            String message = ExceptionEnum.ERROR_INTERNAL_SERVER.getMessage();
            LOGGER.error(exception.getMessage(), exception);
            if (exception instanceof EasyRestException) {
                EasyRestException ere = (EasyRestException) exception;
                code = ere.getCode();
                message = ere.getMessage();
            } else {
                Integer exceptionCode = exceptionMapping.get(exception.getClass().getName());
                if (exceptionCode != null) {
                    String exceptionMessage = exceptionEnumMapping.get(exceptionCode);
                    if (exceptionMessage != null) {
                        code = exceptionCode;
                        message = exceptionMessage;
                    }
                }
            }
            responseModel = new ResponseModel(code, message, null);
        }
        return responseModel;
    }
    @PostConstruct
    public void init() {
        exceptionMapping.put(MethodArgumentTypeMismatchException.class.getName(), ExceptionEnum.ERROR_BAD_REQUEST.getCode());
        exceptionMapping.put(MissingServletRequestParameterException.class.getName(), ExceptionEnum.ERROR_BAD_REQUEST.getCode());
        exceptionMapping.put(NoHandlerFoundException.class.getName(), ExceptionEnum.ERROR_NOT_FOUND.getCode());
        Iterator<ExceptionEnum> iterator = Arrays.stream(ExceptionEnum.values()).iterator();
        while (iterator.hasNext()){
            ExceptionEnum next = iterator.next();
            exceptionEnumMapping.put(next.getCode(),next.getMessage());
        }
    }

}
