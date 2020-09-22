package com.springcloudbase.base.exception;

/**
 * @author Mirko
 * @Description
 * @createTime 2020年09月22日 23:28:00
 */
public enum BaseEnums implements ErrorType {

    SYSTEM_ERROR("-001","系统异常"),
    SYSTEM_SUCCESS("000","处理成功");


    private String code;
    private String msg;

    BaseEnums(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
