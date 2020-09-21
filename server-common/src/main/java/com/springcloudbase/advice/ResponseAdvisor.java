package com.springcloudbase.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springcloudbase.annotation.IgnoreResponse;
import com.springcloudbase.vo.result.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import sun.misc.Resource;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * 结果统一返回处理
 * 如果是统一处理，是不能完成需求的，需要做到精准控制。
 * 具体方案：
 * 1、配置是否需要统一返回，默认不需要。
 * 2、如果需要的前提下，控制单个方法返回是否需要统一返回，这里可以通过方法，或者类的注解来处理
 *
 * yml配置统一结果返回
 * responseBodyAdvice:
 *   is-open: true  # 是否开启结果统一返回
 *   # com.springcloudbase.advice.ResponseAdvisor，多个包配置'com.springcloud.other.provider.controller,com.springcloud.other.provider.service.impl'
 *   basePackages:  "com.springcloud.other.provider.controller"
 *
 * Created by Mirko on 2020/4/12.
 */
@ControllerAdvice
public class ResponseAdvisor implements ResponseBodyAdvice<Object> {


    @Value("${responseBodyAdvice.is-open:false}")
    private boolean isOpen;


    private final Map<Method, Boolean> supportsCache = new WeakHashMap<>();

    @Value("#{'${responseBodyAdvice.basePackages:[]}'.split(',')}")
    String[] basePackages;

    private ObjectMapper objectMapper = new ObjectMapper();

    public ResponseAdvisor() {

    }


    /**
     * 是否需要统一返回
     * @param returnType 执行的方法
     * @return
     */
    private boolean getIsSupport(MethodParameter returnType) {
        Class<?> declaringClass = returnType.getMember().getDeclaringClass();

        IgnoreResponse classIgnore = declaringClass.getAnnotation(IgnoreResponse.class);
        IgnoreResponse methodIgnore = returnType.getMethod().getAnnotation(IgnoreResponse.class);
        //如果没有加注解，或者返回类型是ResponseBean类型，就不走统一返回
        if (classIgnore != null || methodIgnore != null || Result.class.equals(returnType.getGenericParameterType())) {
            return false;
        }
        for (int i = 0; i < basePackages.length; i++) {
            if (declaringClass.getPackage().getName().startsWith(basePackages[i])) {
                return true;
            }
        }
        return false;
    }


    /**
     * 是否做拦截
     * @param methodParameter 执行的具体方法
     * @param aClass 消息转换
     * @return true 拦截  false 不拦截
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        if (!isOpen) {
            return false;
        }
        if (supportsCache.containsKey(methodParameter.getMethod())) {
            return supportsCache.get(methodParameter.getMethod());
        }
        boolean isSupport = getIsSupport(methodParameter);
        supportsCache.put(methodParameter.getMethod(), isSupport);
        return isSupport;
    }

    /**
     * 拦截后处理，这里可以做统一的加密处理，统一的返回结果
     * @param body 执行后返回结果
     * @param methodParameter  执行的具体方法
     * @param mediaType 请求类型
     * @param aClass 消息转换
     * @param serverHttpRequest request
     * @param serverHttpResponse response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (body instanceof Resource) {
            return body;
        }
        Result<Object> result = new Result<>();
        result.setData(body);
        if (methodParameter.getGenericParameterType().equals(String.class)) {
            try {
                serverHttpResponse.getHeaders().set("Content-Type", "application/json;charset=utf-8");
                return objectMapper.writeValueAsString(result);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
