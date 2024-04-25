package com.hq.treasurechest.exception;

// 自定义通用异常类
public class CommonException extends RuntimeException{

    private Integer code;
    private String message;

    public CommonException() {}

    public CommonException(String message) {
        this.message = message;
    }

    public CommonException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
