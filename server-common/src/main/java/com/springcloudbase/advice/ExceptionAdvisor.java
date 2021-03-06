package com.springcloudbase.advice;

import com.springcloudbase.config.CommonConfig;
import com.springcloudbase.exception.BusinessException;
import com.springcloudbase.vo.result.ResponseBean;
import com.springcloudbase.vo.result.ResponseEnums;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.net.ConnectException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Created by Mirko on 2020/4/12.
 */
@Slf4j
@RestControllerAdvice(annotations={RestController.class, Controller.class})
public class ExceptionAdvisor {


    /**
     * 请求参数类型错误异常的捕获
     * 只要出现校验失败，就结束验证 {@link CommonConfig#getValidator()}
     * @param e
     * @return
     */
    @ExceptionHandler(value={BindException.class})
    @ResponseBody
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    public ResponseBean<String> badRequest(BindException e){
        log.error("occurs error when execute method ,message {}",e.getMessage());
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","));
        log.error("{}错误信息 {}","BindException", message);
        return new ResponseBean<>(false, ResponseEnums.BAD_REQUEST.getCode(),message);
    }

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
     * @param e
     * @return
     */
    @ExceptionHandler(value={ConstraintViolationException.class})
    @ResponseBody
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    public ResponseBean<String> ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        log.info("{}错误信息{}", "ConstraintViolationException", message);
        return new ResponseBean<>(false,"",message);
    }


    /**
     * 处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    public ResponseBean MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        Locale locale = LocaleContextHolder.getLocale();
        log.info("local-info {} ", locale.toString());
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        log.info("{}错误信息：{}", "MethodArgumentNotValidException", message);
        return new ResponseBean<>(false, "024", message);
    }





//
//    /**
//     * 404错误异常的捕获
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value={NoHandlerFoundException.class})
//    @ResponseBody
//    @ResponseStatus(value=HttpStatus.NOT_FOUND)
//    public ResponseBean<String> badRequestNotFound(BindException e){
//        log.error("occurs error when execute method ,message {}",e.getMessage());
//        return new ResponseBean<>(false,null, ResponseEnums.NOT_FOUND);
//    }
//
//    /**
//     * mybatis未绑定异常
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(BindingException.class)
//    @ResponseBody
//    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseBean<String> mybatis(Exception e){
//        log.error("occurs error when execute method ,message {}",e.getMessage());
//        return new ResponseBean<>(false,ResponseEnums.BOUND_STATEMENT_NOT_FOUNT);
//    }


    /**
     * 自定义异常的捕获
     * 自定义抛出异常。统一的在这里捕获返回JSON格式的友好提示。
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value={BusinessException.class})
    @ResponseBody
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public <T> ResponseBean<T> sendError(BusinessException exception, HttpServletRequest request){
        String requestURI = request.getRequestURI();
        log.error("occurs error when execute url ={} ,message {}",requestURI,exception.getMsg());
        if (log.isDebugEnabled()) {
            exception.printStackTrace();
        }
        return new ResponseBean<T>(false, exception.getCode(), exception.getMsg());
    }
    /**
     * 数据库操作出现异常
     * @param e
     * @return
     */
    @ExceptionHandler(value={SQLException.class, DataAccessException.class})
    @ResponseBody
    @ResponseStatus(value= HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean<String> systemError(Exception e){
        log.error("occurs error when execute method ,message {}",e.getMessage());
        return new ResponseBean<>(false, ResponseEnums.DATABASE_ERROR);
    }
    /**
     * 网络连接失败！
     * @param e
     * @return
     */
    @ExceptionHandler(value={ConnectException.class})
    @ResponseBody
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseBean<String> connect(Exception e){
        log.error("occurs error when execute method ,message {}",e.getMessage());
        return new ResponseBean<>(false, ResponseEnums.CONNECTION_ERROR);
    }

    @ExceptionHandler(value={Exception.class})
    @ResponseBody
    @ResponseStatus(value=HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseBean<String> notAllowed(Exception e){
        log.error("error --> ", e);
        log.error("occurs error when execute method ,message {}",e.getMessage());
        return new ResponseBean<>(false, ResponseEnums.METHOD_NOT_ALLOWED);
    }

}
