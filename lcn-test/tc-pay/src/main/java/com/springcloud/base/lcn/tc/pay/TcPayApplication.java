package com.springcloud.base.lcn.tc.pay;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDistributedTransaction
public class TcPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TcPayApplication.class, args);
    }

}
