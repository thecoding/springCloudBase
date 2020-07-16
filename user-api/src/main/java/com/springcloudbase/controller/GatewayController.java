package com.springcloudbase.controller;


import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import com.springcloudbase.service.other.OtherServiceExtends;
import com.springcloudbase.service.other.OtherServiceInterface;
import com.springcloudbase.vo.result.ResponseBean;
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
        ResponseBean<String> responseBean = otherServiceInterface.getHelloTest("user");
        Map map = Maps.newHashMap();
        map.put("返回的值", JSONUtil.toJsonStr(responseBean));
        map.put("data", responseBean.getData());
        map.put("user-server", "test");
        return map;
    }
}
