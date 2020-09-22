package com.springcloudbase.oauth2.config;

import com.springcloudbase.webserver.application.ApplicationContextUtil;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Created by Mirko on 2020/4/30.
 */
@Configuration
public class CommonConfig implements ApplicationContextAware {


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
     * 如果直接创建特定的转换器，就会导致其他转换器用不了
     * 比如在 {@link com.springcloudbase.controller.GatewayController#getOrder1()}调用了其他服务，返回了String类型，就解析不了
     *
     * 之前为什么要加这个呢？ 因为restController接口返回String类型，解析不了
     *
     * 问题是：加了调用其他服务，返回String类型解析不了；
     *    Could not extract response: no suitable HttpMessageConverter found for response type [class java.lang.String] and content type [text/plain;charset=UTF-8]
     *
     *    不加自己服务返回String解析不了
     *
     *
     * 解决方案：试试在feign 配置自定义的converters
     * @return
     */
//    @Bean
//    public HttpMessageConverters converters() {
//        List<HttpMessageConverter<?>> converters = Lists.newArrayList();
//        converters.add(new MappingJackson2HttpMessageConverter());
//        converters.add(new ResourceHttpMessageConverter());
//        return new HttpMessageConverters(false, converters);
//    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.setApplicationContext(applicationContext);
    }
}
