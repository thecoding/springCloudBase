package com.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@ComponentScan(value = {
        "com.springcloud",
        "com.springcloudbase"
})
public class OtherProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OtherProviderApplication.class, args);
    }

}
