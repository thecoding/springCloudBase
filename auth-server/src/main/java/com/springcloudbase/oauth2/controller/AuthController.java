package com.springcloudbase.oauth2.controller;

import com.springcloudbase.oauth2.config.JwtProperties;
import com.springcloudbase.oauth2.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mirko on 2020/2/18.
 */

@Controller
@RequestMapping("/auth")
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {


    @Autowired
    AuthService authService;

    @Autowired
    JwtProperties jwtProperties;

    /**
     * 登录接口
     * @param username
     * @param password
     * @return
     * @throws Exception
     */
    @PostMapping("/login")
    public ResponseEntity auth(@NotNull @RequestParam("username") String username, @NotNull @RequestParam("password") String password) throws Exception {
        Map<String, String> map = new HashMap<>();
        String token = authService.getToken(username, password);
        if (StringUtils.isNotBlank(token)) {
            map.put("token", token);
            return ResponseEntity.ok(map);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
    }

}
