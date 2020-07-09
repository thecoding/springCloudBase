package com.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import javax.annotation.PostConstruct;

/**
 * redis session 依赖于jedis服务，如果没有开启jedis配置，需要加载jedis配置信息
 * Created by Mirko on 2020/4/12.
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800 * 3)
public class SessionConfig{

    private static final transient Logger log = LoggerFactory.getLogger(SessionConfig.class);

    @PostConstruct
    public void sessionInit(){
        log.debug(" session init ....");
    }

//    ImportBeanDefinitionRegistrar 接口 实现配置信息注入
//    @Override
//    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
//        BeanDefinitionBuilder jedisRedisConfig = BeanDefinitionBuilder.rootBeanDefinition(JedisRedisConfig.class);
//        beanDefinitionRegistry.registerBeanDefinition("jedisRedisConfig", jedisRedisConfig.getBeanDefinition());
//    }
}
