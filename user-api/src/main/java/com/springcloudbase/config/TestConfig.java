package com.springcloudbase.config;

import org.aspectj.lang.annotation.Aspect;
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
@Aspect
@Configuration
public class TestConfig implements ImportAware {

    @PostConstruct
    public void init(){
        System.out.println(" test config init");
    }

    @Bean
    public Map<String,String> getMap(){
        Map<String, String> map = new HashMap<>();
        map.put("test", "111");
        return map;
    }

    @Override
    public void setImportMetadata(AnnotationMetadata annotationMetadata) {
        Map<String, Object> annotationAttributes = annotationMetadata.getAnnotationAttributes(ApplicationContextConfig.class.getName());
    }
}
