package com.springcloudbase.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;

/**
 * Created by Mirko on 2020/3/12.
 */
@SpringBootApplication(exclude = {SessionAutoConfiguration.class})
@MapperScan("com.springcloudbase.order.dao")
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
