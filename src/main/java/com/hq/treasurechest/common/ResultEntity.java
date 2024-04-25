package com.hq.treasurechest.common;

import java.io.Serial;
import java.io.Serializable;
public class ResultEntity<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int code;
    private String msg;

    private T data;

    public ResultEntity(){}

    public ResultEntity(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultEntity(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResultEntity<T> success(int code, String msg) {
        return new ResultEntity<>(code, msg);
    }

    public static <T> ResultEntity<T> success(int code, String msg, T data) {
        return new ResultEntity<T>(code, msg, data);
    }

    public static <T> ResultEntity<T> failed(int code, String msg) {
        return new ResultEntity<>(code, msg);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
