package com.springcloudbase.base.vo;


import com.springcloudbase.base.exception.BaseEnums;
import com.springcloudbase.base.exception.BaseException;
import com.springcloudbase.base.exception.ErrorType;
import lombok.Getter;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZonedDateTime;

/**
 * Created by Mirko on 2020/4/12.
 */
@Getter
public class Result<T> implements Serializable {

    private static final String SUCCESS_CODE = "000";
    private static final String SUCCESS_MSG = "处理成功";

    private T data;
    private String errCode;
    private String errMsg;
    private Instant time;

    public Result() {
        this.time = ZonedDateTime.now().toInstant();
    }

    private Result(ErrorType enums) {
        this.errCode = enums.getCode();
        this.errMsg = enums.getMsg();
        time = ZonedDateTime.now().toInstant();
    }

    private Result(ErrorType enums, T data) {
        this(enums);
        this.data = data;
    }

    private Result(T data) {
        this(BaseEnums.SYSTEM_SUCCESS);
        this.data = data;
    }

    private Result(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.time = ZonedDateTime.now().toInstant();
    }

    public Result(String errCode, String errMsg, T data) {
        this(errCode, errMsg);
        this.data = data;
    }




    public static Result success(Object data) {
        return new Result<>(data);
    }

    public static Result success(){
        return new Result<>(BaseEnums.SYSTEM_SUCCESS);
    }

    public static Result fail(){
        return fail(BaseEnums.SYSTEM_ERROR);
    }

    public static Result fail(ErrorType errorType) {
        return new Result<>(errorType);
    }

    public static Result fail(BaseException exception) {
        return new Result<>(exception.getErrorType());
    }

    /**
     * 系统异常类并返回结果数据
     *
     * @param errorType
     * @param data
     * @return Result
     */
    public static Result fail(ErrorType errorType, Object data) {
        return new Result<>(errorType, data);
    }
}
