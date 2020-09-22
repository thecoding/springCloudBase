package com.springcloudbase.user.controller;


import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import com.springcloudbase.user.other.OtherServiceExtends;
import com.springcloudbase.user.other.OtherServiceInterface;
import com.springcloudbase.base.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/gateway")
public class GatewayController {

    @Autowired(required = false)
    OtherServiceInterface otherServiceInterface;

    @Autowired
    OtherServiceExtends otherServiceExtends;

    @GetMapping(value = "/order2")
    public Map getOrder2(){
        String order2 = otherServiceExtends.getHelloTest("order2");
        Map map = Maps.newHashMap();
        map.put("other-return", order2);
        map.put("user-server", "test");
        return map;
    }



    @GetMapping(value = "/order1")
    public Map getOrder1(){
        Result<String> result = otherServiceInterface.getHelloTest("user");
        Map map = Maps.newHashMap();
        map.put("返回的值", JSONUtil.toJsonStr(result));
        map.put("data", result.getData());
        map.put("user-server", "test");
        return map;
    }
}
