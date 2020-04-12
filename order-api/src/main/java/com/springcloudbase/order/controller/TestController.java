package com.springcloudbase.order.controller;

import com.springcloudbase.order.event.HelloEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Mirko on 2020/3/26.
 */
@RestController
public class TestController implements ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @RequestMapping("/event")
    public String createEvent(){
        HelloEvent helloEvent = new HelloEvent(this,"createEvent");
        logger.info("{} 发布事件 ", Thread.currentThread().getName());
        this.applicationContext.publishEvent(helloEvent);
        return "已发布事件";
    }

    @Async
    @EventListener
    public void helloEventListener(HelloEvent helloEvent){
        logger.info("{} 收到helloEvent事件，事件名为 {}，创建时间为 {}",Thread.currentThread().getName(),helloEvent.getName(),helloEvent.getCreateDate());
    }


    /**
     * 上下文刷新
     * @param event
     */
    @EventListener
    public void refreshEvent(ContextRefreshedEvent event) {

    }

}
