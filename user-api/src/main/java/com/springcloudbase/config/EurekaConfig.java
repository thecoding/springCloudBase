package com.springcloudbase.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@ConditionalOnProperty(prefix = "eureka.client.service-url", value = "defaultZone", matchIfMissing = true)
@Slf4j
public class EurekaConfig {

    @PostConstruct
    public void init(){
      log.info(" {} 加载信息...",this.getClass().getName());
    }

//    @EnableAutoConfiguration
    public void EurekaConfig(){

    }

}
