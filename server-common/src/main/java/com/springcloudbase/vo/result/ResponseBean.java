package com.springcloudbase.vo.result;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Mirko on 2020/4/12.
 */
@Data
public class ResponseBean<T> implements Serializable {

    private boolean success;
    private T data;
    private String errCode;
    private String errMsg;

    public ResponseBean(){}

    public ResponseBean(boolean success, T data) {
        super();
        this.success = success;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "success=" + success +
                ", data=" + data +
                ", errCode='" + errCode + '\'' +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }

    public ResponseBean(boolean success, T data, String errCode, String errMsg) {
        super();
        this.success = success;
        this.data = data;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public ResponseBean(boolean success, String errCode, String errMsg) {
        this.success = success;
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
    public ResponseBean(boolean success, ResponseEnums enums){
        this.success=success;
        this.errCode=enums.getCode();
        this.errMsg=enums.getMsg();
    }
    public ResponseBean(boolean success,T data, ResponseEnums enums) {
        this.success = success;
        this.data = data;
        this.errCode = enums.getCode();
        this.errMsg = enums.getMsg();
    }

}
