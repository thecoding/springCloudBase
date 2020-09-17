package com.springcloud.base.webautomation;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LoginInterceptor implements HttpInterceptor{


    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);



    @Override
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {

    }

    @Override
    public void process(HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        final StringBuilder cookies = new StringBuilder();
        Lists.newArrayList(httpResponse.getAllHeaders()).stream()
                .forEach(header -> {
                    if(StringUtils.equalsIgnoreCase(header.getName(), "Set-Cookie"))
                    {
                        String cookie = header.getValue();
                        if(StringUtils.isNotBlank(cookie))
                            cookies.append(StringUtils.split(cookie,";")[0]).append(";");
                    }
                    logger.info("name:{},value:{}",header.getName(),header.getValue());
                });

//        cookieRepository.setCookie(form, String.valueOf(cookies)); // 将cookie放入Redis中存储
        System.out.println(cookies);
    }
}
