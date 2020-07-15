package com.springcloudbase.controller;

import com.springcloudbase.application.ApplicationContextUtil;
import com.springcloudbase.config.CommonConfig;
import com.springcloudbase.exception.BusinessException;
import com.springcloudbase.service.UserService;
import com.springcloudbase.vo.UserDataInfo;
import com.springcloudbase.vo.UserRegisterInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.stream.Collectors;

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
