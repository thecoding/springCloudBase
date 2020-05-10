package com.springcloudbase.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


/**
 * Created by Mirko on 2020/4/12.
 */
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800 * 3)
public class SessionConfig {
}
