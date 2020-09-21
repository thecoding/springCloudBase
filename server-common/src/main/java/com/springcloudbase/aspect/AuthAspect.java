package com.springcloudbase.aspect;

import cn.hutool.json.JSONUtil;
import com.springcloudbase.application.ApplicationContextUtil;
import com.springcloudbase.filter.BaseFilter;
import com.springcloudbase.util.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Mirko on 2020/4/11.
 */
@Aspect
@Component
@Order(value = 1)
@Slf4j
public class AuthAspect {

    private List<BaseFilter> filterList = new ArrayList<>();

    @PostConstruct
    public void initAspectFilter(){
        Map<String, BaseFilter> filterMap = ApplicationContextUtil.getApplicationContext().getBeansOfType(BaseFilter.class);
        if (null != filterMap && !filterMap.isEmpty()) {
            filterMap.values().stream().sorted().forEach(filter -> {
                filterList.add(filter);
                log.info("addBmsFilter: {}", filter.getClass().getName());
            });
        }
    }


    @Pointcut("execution(* com.springcloudbase.controller..*.*(..))")
    public void authAspectPoint(){

    }

    @Pointcut("execution(* com.springcloudbase.service..*.*(..))")
    public void servicePoint(){

    }


    /**
     * 前置通知, 在方法执行之前执行
     * 切面进入进执行，通常可以用来处理检验数据，打印参数
     * @param joinPoint
     */
    @Before(value = "authAspectPoint()")
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        log.info("controller before method {} invoke", methodName);
    }

    @Before(value = "servicePoint()")
    public void servicePoint(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        log.info("service before method {} invoke", methodName);
    }

    /**
     * 后置通知， 在方法执行之后执行，目标方法出现异常，依然会执行
     * @param joinPoint
     */
    @After("authAspectPoint()")
    public void afterMethod(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("after method {} invoke", name);
    }


    /**
     * 返回通知， 在方法返回结果之后执行，出现异常时，不执行返回通知方法
     * @param point
     * @param result
     */
    @AfterReturning(value="authAspectPoint()", returning = "result")
    public void afterRunning(JoinPoint point, Object result){
        String methodName = point.getSignature().getName();
        List<Object> args = Arrays.asList(point.getArgs());
        log.info("连接点方法为：" + methodName + ",参数为：" + args + ",目标方法执行结果为：" + result);
    }

    /**
     * 只有在出现异常才会进入，这里只是做了记录没有处理
     * @param point
     * @param throwable
     */
    @AfterThrowing(value="authAspectPoint()",throwing = "throwable")
    public void afterThrow(JoinPoint point, Throwable throwable) {
        // 保存异常日志记录
        log.error("异常方法: {} 发生异常时间 {} 抛出异常：{}" , ((MethodSignature)point.getSignature()).getMethod(), LocalDateTime.now(), throwable.getMessage());
    }



    /*****************/


    /**
     * 统一都返回json，不考虑返回视图的情况
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("authAspectPoint()")
    public Object aroundAuthAround(ProceedingJoinPoint pjp) throws Throwable {
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        RequestUtil.setAttribute(method);
        log.info("method {} invoke ", method);
        try {
            preExecute();
            if (RequestUtil.hasResult()) {
                return JSONUtil.toJsonStr(RequestUtil.getResult());
            }
            return pjp.proceed();
        } finally {
            postExecute();
        }
    }

    private void postExecute() {
        filterList.stream().forEach(filter -> {
            filter.afterExecute();
        });
    }

    private void preExecute() {
        filterList.stream().forEach(filter -> {
            filter.preExecute();
            if(RequestUtil.hasResult()){
                return;
            }
        });
    }

}
