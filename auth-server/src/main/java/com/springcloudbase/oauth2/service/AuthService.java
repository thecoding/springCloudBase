package com.springcloudbase.oauth2.service;

import com.springcloudbase.webserver.client.UserApiClient;
import com.springcloudbase.oauth2.config.JwtProperties;
import com.springcloudbase.base.jwt.pojo.UserInfo;
import com.springcloudbase.base.vo.UserDataInfo;
import com.springcloudbase.base.jwt.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mirko on 2020/2/18.
 */

@Service
public class AuthService {

    @Autowired
    UserApiClient userApiClient;

    @Autowired
    JwtProperties jwtProperties;

    public String getToken(String username, String password) {
        try {
            UserInfo userInfo = new UserInfo();
            UserDataInfo userDataInfo = userApiClient.getUserInfoByName(username, password);
            userInfo.setId(userDataInfo.getId());
            userInfo.setUsername(userDataInfo.getName());
            System.out.println(userInfo.toString());
            return JwtUtils.generateToken(userInfo, jwtProperties.getPrivateKey(), jwtProperties.getExpire());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
