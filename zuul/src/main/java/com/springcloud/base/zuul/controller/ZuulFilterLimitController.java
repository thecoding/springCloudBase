package com.springcloud.base.zuul.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
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




    @RequestMapping("/sentinel")
    @SentinelResource(value = "ZuulFilterLimitController.sentinelSourceTest", blockHandler = "sentinelFall")
    public String sentinelSourceTest(){
        return "sentinel test";
    }

    public String sentinelFall(BlockException e){
        System.out.println("sentinel 阻塞了..");
        return "sentinel fail";
    }

}
