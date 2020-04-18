package com.springcloudbase.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * Created by Mirko on 2020/4/15.
 */
@Component
public class ComponentInterceptor implements BeanPostProcessor, InstantiationAwareBeanPostProcessor {

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (beanName.equals("component")) {
            System.out.println("component 实例化之后");
        }
        return false;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanName.equals("component")) {
            System.out.println("component 实例化之前");
        }
        return null;
    }



    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("component")) {
            System.out.println("component 构造方法之前");
        }
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("component")) {
            System.out.println("component 构造方法之后");
        }
        return null;
    }


}
