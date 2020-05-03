package com.springcloudbase.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Arrays;

/**
 * Created by Mirko on 2020/4/30.
 */
@Configuration
public class CommonConfig {


//    @Autowired
//    MessageSource messageSource;


    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor(@Qualifier("getValidator") @Lazy Validator validator) {
        MethodValidationPostProcessor methodValidationPostProcessor = new MethodValidationPostProcessor();
        methodValidationPostProcessor.setValidator(validator);
        return methodValidationPostProcessor;
    }


    /**
     * todo 国际化、不能切换
     * @return
     */
    @Bean
    @Lazy
    public Validator getValidator(){
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("i18n/validations");
//        messageSource.setDefaultEncoding("utf-8");
//        MessageSourceResourceBundleLocator messageSourceResourceBundleLocator = new MessageSourceResourceBundleLocator(messageSource);

        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
//                .messageInterpolator(new LocaleContextMessageInterpolator(new ResourceBundleMessageInterpolator(messageSourceResourceBundleLocator)))
                // 快速失败 - 只要出现校验失败的情况，就立即结束校验，不再进行后续的校验
                .failFast(true)
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }



    /**
     * http 消息转换器
     * @return
     */
    @Bean
    public HttpMessageConverters converters() {
        return new HttpMessageConverters(false, Arrays.asList(new MappingJackson2HttpMessageConverter(), new ResourceHttpMessageConverter()));
    }

}
