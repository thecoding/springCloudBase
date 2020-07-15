package com.springcloudbase.config;

import com.springcloudbase.enable.EnableRedisSession;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 设置全局applicationContext
 * Created by Mirko on 2020/4/11.
 */
@Configuration
@Import({TestConfig.class})
@EnableRedisSession
public class UserConfig implements BeanNameAware {


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

    @Override
    public void setBeanName(String name) {
        System.out.printf("name {}",name);
    }
}
