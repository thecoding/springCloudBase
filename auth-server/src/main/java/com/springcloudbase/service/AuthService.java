package com.springcloudbase.service;

import com.springcloudbase.client.UserApiClient;
import com.springcloudbase.config.JwtProperties;
import com.springcloudbase.jwt.pojo.UserInfo;
import com.springcloudbase.utils.BeanUtil;
import com.springcloudbase.vo.UserDataInfo;
import org.springframework.beans.BeanUtils;
import utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
