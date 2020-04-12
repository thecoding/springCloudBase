package com.springcloudbase;

import com.springcloudbase.config.TestConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * ComponentScan 后面是过滤TestConfig.class类的加载
 *
 * @author Mirko
 * @Description
 * @createTime 2020年02月19日 00:08:00
 */
@SpringBootApplication(scanBasePackages = "com.springcloudbase")
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { TestConfig.class})})
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);
    }

}
