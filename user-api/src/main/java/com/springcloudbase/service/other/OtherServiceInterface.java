package com.springcloudbase.service.other;

import com.springcloudbase.vo.result.ResponseBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;


//@FeignClient(value = "other-api")
public interface OtherServiceInterface{

    @GetMapping(value = "/user/hello")
    ResponseBean<String> getHelloTest(@RequestParam(value = "name") String name);
}
