package com.springcloud.base.lcn.tc.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Mirko
 * @Description
 * @createTime 2020年11月10日 06:18:00
 */
@RestController
public class OrderController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/order")
    public String order(){
        return null;
    }
}
