package com.easyspring.core.exception;

public class EasyRestException  extends Exception{

    private Integer code;
    private String message;

    public EasyRestException(Exception e){
        super(e);
        this.message = e.getMessage();
    }

    public EasyRestException(String message){
        super(message);
        this.message = message;
    }

    public EasyRestException(Integer code, String message){
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
