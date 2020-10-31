package com.springcloudbase.gateway.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Slf4j
public class AutoConfiguration {



    @PostConstruct
    public void init(){
        log.debug("自动配置化开启");
    }

    @Configuration
    @ConditionalOnExpression("T(org.springframework.util.StringUtils).isEmpty('${eureka.client.service-url.defaultZone}')")
    @EnableAutoConfiguration(exclude = {EurekaClientAutoConfiguration.class})
    @Slf4j
    public static class EurekaConfig{

        @Value("eureka.client.service-url.defaultZone")
        String url;

        public EurekaConfig(){
            log.info("eureka url : " + url);
            log.info("没有eureka配置地址，不开启eureka-client");
        }
    }
}
