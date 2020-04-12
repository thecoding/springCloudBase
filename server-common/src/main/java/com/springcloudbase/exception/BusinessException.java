package com.springcloudbase.exception;

import com.springcloudbase.vo.result.ResponseEnums;

/**
 * Created by Mirko on 2020/4/12.
 */
public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    protected String code;
    protected String msg;
    protected String message;//打印出的日志信息

    public BusinessException(ResponseEnums enums, String message) {
        super();
        this.code = enums.getCode();
        this.msg = enums.getMsg();
        this.message = message;
    }

    public BusinessException(ResponseEnums enums) {
        super();
        this.code = enums.getCode();
        this.msg = enums.getMsg();
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




    public BusinessException() {
        super();
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(String message) {
        super(message);
    }

}
