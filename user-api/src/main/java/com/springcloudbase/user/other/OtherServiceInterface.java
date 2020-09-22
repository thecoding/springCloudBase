package com.springcloudbase.user.other;

import com.springcloudbase.base.vo.Result;
import org.springframework.web.bind.annotation.*;


//@FeignClient(value = "other-api")
public interface OtherServiceInterface{

    @GetMapping(value = "/user/hello")
    Result<String> getHelloTest(@RequestParam(value = "name") String name);
}
