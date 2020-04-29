package com.springcloudbase.controller;

import com.springcloudbase.service.UserService;
import com.springcloudbase.vo.UserDataInfo;
import com.springcloudbase.vo.UserRegisterInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Mirko
 * @Description
 * @createTime 2020年02月19日 00:08:00
 */
@RestController
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
        }
        return null;
    }


    @RequestMapping("/info")
    public String getInfo() {
        return serverName + " : " +serverPort + userService.getUserName();
    }

    /**
     * 测试@Valid
     * todo 待测试
     * 工具: https://juejin.im/post/5d52a4edf265da03c81516de
     * pojo-to-json 插件
     * 在IDEA中快速测试API接口：https://my.oschina.net/niithub/blog/1920390
     * @param registerInfo 入参
     * @return
     */
    @RequestMapping("/validation")
    public String testValidation(@Valid UserRegisterInfo registerInfo){
        return registerInfo.toString();
    }
}
