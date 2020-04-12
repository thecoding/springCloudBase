package com.springcloudbase.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mirko on 2020/3/17.
 */
@Aspect
@Configuration
public class TestConfig {

    @Bean
    public Map<String,String> getMap(){
        Map<String, String> map = new HashMap<>();
        map.put("test", "111");
        return map;
    }
}
