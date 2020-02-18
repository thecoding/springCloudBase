package com.springcloudbase.service;

import com.springcloudbase.config.JwtProperties;
import com.springcloudbase.jwt.pojo.UserInfo;
import utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mirko on 2020/2/18.
 */

@Service
public class AuthService {

    @Autowired
    JwtProperties jwtProperties;

    public String getToken(String username, String password) {
        try {
            if(username.equals("小明") && password.equals("123456")){
                UserInfo userInfo = new UserInfo();
                userInfo.setUsername("小明");
                userInfo.setId(30L);
                return JwtUtils.generateToken(userInfo, jwtProperties.getPrivateKey(), jwtProperties.getExpire());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
