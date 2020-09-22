package com.springcloudbase.oauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by Mirko on 2020/2/18.
 */
@SpringBootApplication
@EnableFeignClients
public class AuthApplication {


    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class,args);
    }
}
