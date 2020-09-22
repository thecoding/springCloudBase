package com.springcloudbase.webserver.fastdfs.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * @author Mirko
 * @Description
 * @createTime 2020年02月19日 17:17:00
 */
@Configuration
@Import(FdfsClientConfig.class)
//解决jmx重复注册bean的问题
@EnableMBeanExport(registration= RegistrationPolicy.IGNORE_EXISTING)
public class FastDfsConfig {
}
