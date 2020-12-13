package com.springcloudbase.order.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mirko
 * @DescriptionO
 * @createTime 2020年12月13日 14:14:00
 */
@Configuration
public class MyRedissionConfig {

    @Bean
    public RedissonClient redisClient(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://192.168.5.140:6379");
        RedissonClient redissonClient = Redisson.create(config);
        return redissonClient;
    }
}
