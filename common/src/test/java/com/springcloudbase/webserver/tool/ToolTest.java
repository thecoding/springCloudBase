package com.springcloudbase.webserver.tool;

import cn.hutool.aop.ProxyUtil;
import cn.hutool.aop.aspects.SimpleAspect;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.lang.Console;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created by Mirko on 2020/4/10.
 */
public class ToolTest {

    @Test
    public void toolTest1(){
        Animal dog = ProxyUtil.proxy(new Dog(), TimeIntervalAspect.class);
        dog.eat();
    }
}

interface Animal {
    void eat();
}

class Dog implements Animal{

    public void eat(){
        System.out.println("dog eat");
    }
}

class TimeIntervalAspect extends SimpleAspect {

    //TimeInterval为Hutool实现的一个计时器
    private TimeInterval interval = new TimeInterval();

    @Override
    public boolean before(Object target, Method method, Object[] args) {
        interval.start();
        return true;
    }

    @Override
    public boolean after(Object target, Method method, Object[] args) {
        Console.log("Method [{}.{}] execute spend [{}]ms", target.getClass().getName(), method.getName(), interval.intervalMs());
        return true;
    }
}
