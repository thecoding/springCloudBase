package com.springcloud.base.zuul.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mirko
 * @Description
 * @createTime 2020年11月01日 23:56:00
 */
@RestController
public class ZuulFilterLimitController {


    @RequestMapping("/hello")
    public String hello(){
        return "test";
    }

    @RequestMapping("/hello/test")
    public String helloAndTest(){
        return "hello-test";
    }

}
