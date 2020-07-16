package com.springcloud.other.provider.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface OtherService {

    @GetMapping(value = "/user/hello")
    String getHelloTest(@RequestParam("name") String name);

}
