package com.springcloudbase.controller;

import com.springcloudbase.vo.UserDataInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author Mirko
 * @Description
 * @createTime 2020年02月19日 00:08:00
 */
@RestController
public class UserController {


    @RequestMapping("/getUserInfoByName")
    public UserDataInfo getUserByUserName(@NotNull @RequestParam String username, @NotNull @RequestParam String password) {
        if (username.equals("小明") && password.equals("123456")) {
            UserDataInfo userDataInfo = new UserDataInfo();
            userDataInfo.setId(20L);
            userDataInfo.setName("小明");
            return userDataInfo;
        }
        return null;
    }
}
