package com.neuedu.commons;


import java.io.Serializable;

public class serverResponse<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public serverResponse() {
    }

    public serverResponse(Integer code) {
        this.code = code;
    }


    public serverResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public serverResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public serverResponse(Integer code, T data) {
        this.code = code;
        this.data = data;
    }


    // 成功的返回的方法
    public static serverResponse serverSuccess() {
        return new serverResponse(ResponseCode.Code.SUCCESS.getCode());
    }

    public static serverResponse serverSuccess(String message) {
        return new serverResponse(ResponseCode.Code.SUCCESS.getCode(), message);
    }

    public static <T> serverResponse serverSuccess(String message, T data) {
        return new serverResponse(ResponseCode.Code.SUCCESS.getCode(), message, data);
    }

    public static <T> serverResponse serverSuccess(T data) {
        return new serverResponse(ResponseCode.Code.SUCCESS.getCode(), data);
    }

    //失败的方法
    public static serverResponse serverFailed() {
        return new serverResponse(ResponseCode.Code.ERROR.getCode());
    }

    public static serverResponse serverFailed(String message) {
        return new serverResponse(ResponseCode.Code.ERROR.getCode(), message);
    }

    public static serverResponse serverFailed(Integer code, String message) {
        return new serverResponse(code,message);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "serverResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }


}
