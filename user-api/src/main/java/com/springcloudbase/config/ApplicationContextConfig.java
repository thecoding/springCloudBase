package com.springcloudbase.config;

import com.springcloudbase.application.ApplicationContextUtil;
import com.springcloudbase.enable.EnableRedisSession;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Mirko on 2020/5/22.
 */
@Configuration
@Import({TestConfig.class})
@EnableRedisSession
public class ApplicationContextConfig implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.setApplicationContext(applicationContext);
    }
}
