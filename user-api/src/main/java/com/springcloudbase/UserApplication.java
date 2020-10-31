package com.springcloudbase;

import com.springcloudbase.config.TestConfig;
import com.springcloudbase.express.ExpressFalseBean;
import com.springcloudbase.express.ExpressTrueBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
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
@ComponentScan(value = "com.springcloudbase", excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
        classes = { TestConfig.class})})
//开启FeignClients调用
@EnableFeignClients
public class UserApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(UserApplication.class);

        //express测试
//        ExpressTrueBean bean = context.getBean(ExpressTrueBean.class);
//        System.out.println(bean);
//        ExpressFalseBean falseBean = context.getBean(ExpressFalseBean.class);
//        System.out.println(falseBean);
    }

}
