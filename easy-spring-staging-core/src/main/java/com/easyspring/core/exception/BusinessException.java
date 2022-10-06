package com.easyspring.core.exception;

/**
 * 业务异常类
 *
 * @author caobaoyu
 * @date 2022/10/1 22:53
 */
public class BusinessException extends Exception {

    // 异常码
    private Integer code;

    // 异常信息
    private String message;

    public BusinessException(Integer code, String message) {
        super(message);
        this.message = message;
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
