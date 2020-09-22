package com.springcloudbase.user;


import com.springcloudbase.user.config.TestConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * ComponentScan 后面是过滤TestConfig.class类的加载
 *
 * @author Mirko
 * @Description
 * @createTime 2020年02月19日 00:08:00
 */
@SpringBootApplication
//排除加载TestConfig.class
@ComponentScan(value = "com.springcloudbase.user", excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
        classes = { TestConfig.class})})
//开启FeignClients调用
@EnableFeignClients
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
    }

}
