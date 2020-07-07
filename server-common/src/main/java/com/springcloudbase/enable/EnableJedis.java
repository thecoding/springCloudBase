package com.springcloudbase.enable;


import com.config.JedisRedisConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({JedisRedisConfig.class})
@Documented
@Configuration
public @interface EnableJedis {
}
