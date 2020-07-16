package com.springcloudbase.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * Created by Mirko on 2020/5/2.
 */
@Component
@Slf4j
public class RefreshListener {

    @Autowired
    RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event){
        requestMappingHandlerAdapter.getMessageConverters()
                .stream()
                .forEach(v -> {
                    log.debug("所有加载的messageConverters{}",v.getClass());
                });
    }
}
