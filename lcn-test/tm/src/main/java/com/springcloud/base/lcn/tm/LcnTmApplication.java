package com.springcloud.base.lcn.tm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class LcnTmApplication {

    public static void main(String[] args) {
        SpringApplication.run(LcnTmApplication.class, args);
    }

}
