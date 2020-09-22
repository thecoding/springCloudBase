package com.springcloudbase.user.controller;

import com.springcloudbase.webserver.application.ApplicationContextUtil;
import com.springcloudbase.oauth2.config.CommonConfig;
import com.springcloudbase.webserver.exception.BusinessException;
import com.springcloudbase.user.other.UserService;
import com.springcloudbase.base.vo.UserDataInfo;
import com.springcloudbase.user.vo.UserRegisterInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/**
 * @author Mirko
 * @Description
 * @createTime 2020年02月19日 00:08:00
 */
@Slf4j
@RestController
@RequestMapping(value = "/user",method = RequestMethod.GET)
public class UserController {

    @Value("${server.port}")
    String serverPort;

    @Value("${spring.application.name}")
    String serverName;

    @Autowired
    UserService userService;

    @RequestMapping("/getUserInfoByName")
    public UserDataInfo getUserByUserName(@NotNull @RequestParam String username, @NotNull @RequestParam String password) {
        if (username.equals("小明") && password.equals("123456")) {
            UserDataInfo userDataInfo = new UserDataInfo();
            userDataInfo.setId(20L);
            userDataInfo.setName("小明");
            return userDataInfo;
        } else if (password.equals("1")) {
            int i = 1/0;
        }
        throw new BusinessException("111","-101");
    }


    @RequestMapping("/info")
    public String getInfo() {
        CommonConfig commonConfig  = ApplicationContextUtil.getBean("commonConfig");
        System.out.println("userController ---> " + commonConfig.toString());
        return serverName + " : " +serverPort + userService.getUserName();
    }


    @RequestMapping("/registerInfo")
    public String getUserRegisterInfo(@RequestBody UserRegisterInfo userRegisterInfo) {
        return userRegisterInfo.toString();
    }


}
