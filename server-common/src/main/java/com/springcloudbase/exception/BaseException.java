package com.springcloudbase.exception;

import com.springcloudbase.vo.result.ResponseEnums;
import lombok.Getter;

/**
 * @author Mirko
 * @Description
 * @createTime 2020年09月20日 02:06:00
 */
@Getter
public class BaseException extends RuntimeException {

    /**
     * 异常对应的错误类型
     */
    private final ErrorType errorType;

    /**
     * 默认是系统异常
     */
    public BaseException() {
        this.errorType = ResponseEnums.SYSTEM_ERROR;
    }

    public BaseException(ErrorType errorType) {
        this.errorType = errorType;
    }

    public BaseException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public BaseException(ErrorType errorType, String message, Throwable cause) {
        super(message, cause);
        this.errorType = errorType;
    }
}
