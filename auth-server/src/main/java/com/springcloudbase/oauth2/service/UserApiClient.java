package com.springcloudbase.oauth2.service;

import com.springcloudbase.base.vo.UserDataInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author Mirko
 * @Description
 * @createTime 2020年02月19日 00:01:00
 */
@FeignClient(name="user-server", url = "localhost:13000")
public interface UserApiClient {

    @RequestMapping("/getUserInfoByName")
    UserDataInfo getUserInfoByName(@RequestParam String username, @RequestParam String password);
}
