package com.easyspring.core.exception;

public enum ExceptionEnum {
    ERROR_BUSINESS(1000,"业务逻辑错误"),
    ERROR_INTERNAL_SERVER(500,"内部错误"),
    ERROR_NOT_FOUND(404,"无法访问"),
    ERROR_BAD_REQUEST(400,"错误请求"),
    OK(200,"OK");

    private Integer code;
    private String message;

    ExceptionEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return String.format("\n{\ncode:%d,\nmessage:%s\n}\n",code,message);
    }
}
