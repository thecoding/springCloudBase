package com.springcloudbase.gateway;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.*;
import java.util.Map;

/**
 * Created by Mirko on 2020/10/27.
 */
@Target({ElementType.TYPE , ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(ConditionalOnPropertyEmpty.PropertyEmpty.class)
public @interface ConditionalOnPropertyEmpty {

    String value() default "";

    class PropertyEmpty implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Map<String, Object> attr = metadata.getAnnotationAttributes(ConditionalOnPropertyEmpty.class.getName());
            String value = context.getEnvironment().getProperty((String)attr.get("value"));
            return (null == value || value.trim().equals(""));
        }
    }
}
