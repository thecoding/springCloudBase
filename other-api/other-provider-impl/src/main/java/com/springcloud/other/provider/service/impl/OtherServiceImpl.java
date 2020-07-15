package com.springcloud.other.provider.service.impl;


import com.springcloud.other.provider.service.OtherService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class OtherServiceImpl implements OtherService {

    @RequestMapping("/test")
    public String test(){
        return "test";
    }


    @Override
    @RequestMapping(value = "/hello")
    public String getHelloTest(@RequestParam(value = "name",required = false) String name) {
        return name + " hello other service";
    }
}
