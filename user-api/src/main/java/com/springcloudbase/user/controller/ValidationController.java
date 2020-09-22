package com.springcloudbase.user.controller;


import com.springcloudbase.webserver.application.ApplicationContextUtil;
import com.springcloudbase.user.vo.UserRegisterInfo;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.Validator;
import java.util.Map;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/validation")
public class ValidationController {

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
    public String testValidation(@Validated UserRegisterInfo registerInfo){
        return registerInfo.toString();
    }


    @RequestMapping("/validation2")
    public String testValidation2(@Valid @RequestBody UserRegisterInfo userRegisterInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String str = bindingResult.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
            return str;
        }
        return userRegisterInfo.toString();
    }

    @RequestMapping("/getValidation")
    public String getValidation(@RequestBody UserRegisterInfo userRegisterInfo) {
        Map<String, Validator> validationMap = ApplicationContextUtil.getApplicationContext().getBeansOfType(Validator.class);
        validationMap.entrySet().stream().map(e -> "userController -->   Validator -> "+ e.getKey() + " " + e.getValue()).forEach(System.out::println);
        return userRegisterInfo.toString();
    }
}
