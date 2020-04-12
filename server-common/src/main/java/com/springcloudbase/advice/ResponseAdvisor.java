package com.springcloudbase.advice;

import com.springcloudbase.vo.result.ResponseBean;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import sun.misc.Resource;

/**
 * 结果统一返回处理
 * Created by Mirko on 2020/4/12.
 */
@ControllerAdvice
public class ResponseAdvisor implements ResponseBodyAdvice<Object> {


    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (body instanceof Resource) {
            return body;
        }
        if (body instanceof ResponseBean) {
            return body;
        }
        return new ResponseBean(true,body);
    }
}
