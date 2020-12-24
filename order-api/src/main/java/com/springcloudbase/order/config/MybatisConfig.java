package com.springcloudbase.order.config;

import com.github.pagehelper.PageInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mirko
 * @Description
 * @createTime 2020年12月25日 01:39:00
 */
@Configuration
public class MybatisConfig {

    @Bean
    public PageInterceptor pageInterceptor(){
        return new PageInterceptor();
    }
}
