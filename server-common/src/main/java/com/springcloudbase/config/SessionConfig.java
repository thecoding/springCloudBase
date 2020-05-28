package com.springcloudbase.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import javax.annotation.PostConstruct;

/**
 * Created by Mirko on 2020/4/12.
 */
@Configuration
@ConditionalOnProperty("spring.session.redis.namespace")
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800 * 3)
public class SessionConfig {

    @PostConstruct
    public void sessionInit(){
        System.out.println(" session init ....");
    }
}
