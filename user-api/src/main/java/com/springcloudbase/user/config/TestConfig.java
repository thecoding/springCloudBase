package com.springcloudbase.user.config;


import org.apache.catalina.startup.UserConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.type.AnnotationMetadata;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mirko on 2020/3/17.
 */
@Configuration
public class TestConfig implements ImportAware {

    private static final transient Logger log = LoggerFactory.getLogger(TestConfig.class);

    @PostConstruct
    public void init(){
        log.debug(" test config init");
    }

    @Bean
    public Map<String,String> getMap(){
        Map<String, String> map = new HashMap<>();
        map.put("test", "111");
        return map;
    }

    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {
        Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(UserConfig.class.getName());
    }
}
