/**
 * Copyright(C) 2018 Company:easy-spring-staging Co.
 */

package com.easyspring.core.conf.exception;


import com.easyspring.core.exception.BusinessException;
import com.easyspring.core.model.ResponseCode;
import com.easyspring.core.model.ResponseModel;
import com.sun.corba.se.impl.io.TypeMismatchException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.BindException;


/**
 * 异常处理器 .
 *
 * <p>
 * 功能详细描述
 *
 * @author caobaoyu
 * @date 2018/10/29 14:11
 */
@Slf4j
public abstract class AbstractExceptionConfiguration {

    @ResponseBody
    @ExceptionHandler(value = {
            BindException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class,
            TypeMismatchException.class,
            ServletRequestBindingException.class,
            MissingRequestValueException.class,
            HttpMessageConversionException.class
    })
    public ResponseModel<Void> handler400(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseModel.fail(ResponseCode.BAD_REQUEST, null);
    }

    @ResponseBody
    @ExceptionHandler(value = {NoHandlerFoundException.class})
    public ResponseModel<Void> handler404(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseModel.fail(ResponseCode.NOT_FOUND, null);
    }

    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public ResponseModel<Void> handlerBusiness(Exception e) {
        log.error(e.getMessage(), e);
        if (e instanceof BusinessException) {
            BusinessException be = (BusinessException) e;
            return ResponseModel.create(be.getCode(), be.getMessage(), null);
        } else {
            return ResponseModel.fail(ResponseCode.INTERNAL_SERVER_ERROR, null);
        }
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseModel<Void> handler500(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseModel.fail(ResponseCode.INTERNAL_SERVER_ERROR, null);
    }

}
