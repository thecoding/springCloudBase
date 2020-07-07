package com.springcloudbase.log;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * 自定义日志打印，配合logback.xml使用
 * @author Mirko
 * @Description <conversionRule conversionWord="userId" converterClass="com.springcloudbase.log.LoginUserConverter"/> 如果需要打印用户信息，可以在convert方法返回需要打印的内容，
 * @createTime 2020年05月28日 23:36:00
 */
public class LoginUserConverter extends MessageConverter {

    private static final transient Logger logger = LoggerFactory.getLogger(LoginUserConverter.class.getName());
    /**
     * 日志信息通过convert方法都会打印出日志信息，如果只有某个包下面的才输出呢？
     * @param event
     * @return
     */
    @Override
    public String convert(ILoggingEvent event) {
        logger.debug("message --> " + event.getMessage());
        logger.debug("threadName --> " + event.getThreadName());
        logger.debug("getArgumentArray --> " + Arrays.toString(event.getArgumentArray()));
        logger.debug("getLoggerName --> " + event.getLoggerName());
        return "";
    }
}
