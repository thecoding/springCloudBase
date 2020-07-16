package com.springcloud.other.provider.controller;


import com.google.common.collect.Maps;
import com.springcloudbase.annotation.IgnoreResponse;
import com.springcloudbase.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/other")
public class OtherServiceController {

    @RequestMapping("/str")
    @IgnoreResponse
    public String str(@RequestParam(value = "str",required = false)String strs){
        strs = StringUtil.isBlank(strs) ? "没有传值过来，系统默认" : strs;
        return strs;
    }

    @RequestMapping("/map")
    public Map map(@RequestParam(value = "str", required = false) String strs) {
        Map map = Maps.newHashMap();
        map.put("输入的值", strs);
        map.put("系统的值", "默认");
        return map;
    }
}
