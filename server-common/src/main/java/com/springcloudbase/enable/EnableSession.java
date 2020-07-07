package com.springcloudbase.enable;


import com.config.SessionConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({SessionConfig.class})
@Documented
@Configuration
public @interface EnableSession {
}
