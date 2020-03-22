package com.springcloudbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Mirko on 2020/3/10.
 */
@SpringBootApplication
public class GatewayApplication {

    private String string;

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

}
