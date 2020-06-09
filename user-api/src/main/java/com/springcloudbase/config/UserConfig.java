package com.springcloudbase.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 设置全局applicationContext
 * Created by Mirko on 2020/4/11.
 */
@Configuration
@ConditionalOnProperty("spring.redistest")
public class UserConfig{


    @PostConstruct
    public void test() {
        System.out.println(" 加载了。。。 ");
    }

    @Bean
    public Map<String,String> testProperty(){
        Map<String, String> map = new HashMap<>();
        map.put("test", "test1");
        System.out.println("map has create...");
        return map;
    }

}
