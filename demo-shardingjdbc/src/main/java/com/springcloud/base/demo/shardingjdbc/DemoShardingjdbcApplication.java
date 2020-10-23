package com.springcloud.base.demo.shardingjdbc;

import com.springcloud.base.demo.shardingjdbc.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@MapperScan(basePackages = "com.springcloud.base.demo.shardingjdbc.repository")
@SpringBootApplication
public class DemoShardingjdbcApplication {

    public static void main(String[] args) {
        try (ConfigurableApplicationContext run = SpringApplication.run(DemoShardingjdbcApplication.class, args)) {
            UserService userService = run.getBean(UserService.class);
            userService.processUsers();
            userService.getUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
