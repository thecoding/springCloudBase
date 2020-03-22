package com.springcloudbase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mirko on 2020/3/17.
 */
@Configuration
public class Test2Config {

    @Bean
    public List<String> getList(){
        return Arrays.asList("str");
    }
}
