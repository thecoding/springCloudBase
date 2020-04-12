package com.springcloudbase.order.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Created by Mirko on 2020/3/26.
 */
@Configuration
@EnableAsync
public class AppConfig {

    private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

}
