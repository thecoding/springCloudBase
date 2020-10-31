package com.springcloudbase.config;

import com.springcloudbase.express.ExpressFalseBean;
import com.springcloudbase.express.ExpressTrueBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author Mirko
 * @Description
 * @createTime 2020年10月24日 01:09:00
 */
@Configuration
@Slf4j
public class ExpressConfig {

//    @Value(value = "${eureka.client.service-url.defaultZone}")
//    String defaultZone;
//
//    @PostConstruct
//    public void init(){
//        log.info(ExpressConfig.class.getName()+"  --> "+ defaultZone);
//    }

    @Bean
//    @ConditionalOnExpression("!T(org.springframework.util.StringUtils).isEmpty('${eureka.client.service-url.defaultZone:}')")
    public ExpressTrueBean expressTrueBean(){
        return new ExpressTrueBean("express = true");
    }

    @Bean
//    @ConditionalOnExpression("'${eureka.client.service-url.defaultZone}' == null or '${eureka.client.service-url.defaultZone}' == ''")
    public ExpressFalseBean expressFalseBean(){
        return new ExpressFalseBean("express false");
    }
}
