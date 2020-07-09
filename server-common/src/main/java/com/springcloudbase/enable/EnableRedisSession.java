package com.springcloudbase.enable;


import com.config.JedisRedisConfig;
import com.config.SessionConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;


/**
 * session依赖于redis，当前redis用的是jedis，开启了redis-session默认需要开启jedis配置信息
 * 除了在这里加@Import 还可以在SessionConfig实现接口ImportBeanDefinitionRegistrar来注入redis配置类信息
 * {@link SessionConfig} session配置
 * {@link JedisRedisConfig} redis配置类
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({JedisRedisConfig.class, SessionConfig.class})
@Documented
@Configuration
public @interface EnableRedisSession {
}
