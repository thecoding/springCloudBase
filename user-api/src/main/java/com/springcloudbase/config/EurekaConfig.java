package com.springcloudbase.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
public class EurekaConfig {

    @PostConstruct
    public void init(){
      log.info(" {} 加载信息...",this.getClass().getName());
    }


    /**
     * 目的：如果配置了defaultZone就加载EurekaClientAutoConfiguration，如果没有配置就不加载
     * 配置是否自动加载eureka客户端自动配置类
     * matchIfMissing: 如果未设置属性是否匹配，默认为false(不通过，不加载)
     * 这里设置为true，表示如果没有配置defaultZone将加载EurekaAutoConfig类
     */
    @Slf4j
    @Configuration
    @ConditionalOnProperty(name = {"eureka.client.service-url.defaultZone"}, matchIfMissing = true)
    @EnableAutoConfiguration(exclude = {EurekaClientAutoConfiguration.class})
    public static class EurekaAutoConfig{
        public EurekaAutoConfig(){
            log.debug("没有开启eureka..");
        }
    }

}
