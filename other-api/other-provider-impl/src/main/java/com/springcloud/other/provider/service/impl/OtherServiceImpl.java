package com.springcloud.other.provider.service.impl;


import com.springcloud.other.provider.service.OtherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@RestController
@Slf4j
public class OtherServiceImpl implements OtherService {

    @Value("#{'${responseBodyAdvice.basePackages}'.split(',')}")
    String[] basePackages;

    @PostConstruct
    public void init(){
        if (basePackages != null) {
            Arrays.stream(basePackages).forEach(v -> {
                log.info(v);
            });
        }
    }

    @RequestMapping("/user/test")
    public String test(){
        return "test";
    }


    @Override
    public String getHelloTest(@RequestParam(value = "name",required = false) String name) {
        return name + " hello other service";
    }
}
