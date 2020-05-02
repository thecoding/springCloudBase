package com.springcloudbase.exception;

import com.springcloudbase.vo.result.ResponseEnums;

/**
 * Created by Mirko on 2020/4/12.
 */
public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    protected String code;
    protected String msg;

    public BusinessException(ResponseEnums enums, String message) {
        super();
        this.code = enums.getCode();
        this.msg = enums.getMsg();
    }

    public BusinessException(String msg) {
        super();
        this.msg = msg;
    }


    public BusinessException(String msg,String code) {
        super();
        this.msg = msg;
        this.code = code;
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


}
