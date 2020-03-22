package com.springcloudbase;

import com.springcloudbase.config.TestConfig;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

/**
 * @author Mirko
 * @Description
 * @createTime 2020年02月19日 00:08:00
 */
@SpringBootApplication(scanBasePackages = "com.springcloudbase")
@ComponentScan(excludeFilters = {@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = { TestConfig.class})})
public class UserApplication implements ApplicationContextAware {

    ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class);

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }
    }
}
