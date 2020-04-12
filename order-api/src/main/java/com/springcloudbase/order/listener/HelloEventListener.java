package com.springcloudbase.order.listener;

import com.springcloudbase.order.event.HelloEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Created by Mirko on 2020/3/26.
 */
@Component
public class HelloEventListener implements ApplicationListener<HelloEvent> {


    private static final Logger logger = LoggerFactory.getLogger(HelloEventListener.class);

    @Override
    public void onApplicationEvent(HelloEvent helloEvent) {
        logger.info("{} 收到helloEvent事件，事件名为 {}，创建时间为 {}", Thread.currentThread().getName() ,helloEvent.getName(),helloEvent.getCreateDate());
    }
}
