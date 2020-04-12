package com.springcloudbase.jwt;

import com.springcloudbase.jwt.pojo.UserInfo;
import org.junit.Before;
import org.junit.Test;
import com.springcloudbase.jwt.utils.JwtUtils;
import com.springcloudbase.utils.RsaUtils;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by Mirko on 2020/2/18.
 */
public class JwtTest {


    private String pubKeyPath = "/Users/zhouchao/github/springCloudBase/auth-server/tmp/rsa/rsarsa.pub";
    private String priKeyPath = "/Users/zhouchao/github/springCloudBase/auth-server/tmp/rsa/rsa.pri";

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @Test
    public void createKey() throws Exception{
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void getKey() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
        System.out.println("---------public key---------");
        System.out.println(publicKey);
        System.out.println("---------private key---------");
        System.out.println(privateKey);
    }


    @Test
    public void generateToken() throws Exception {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(20L);
        userInfo.setUsername("小明");
        String token = JwtUtils.generateToken(userInfo, privateKey, 10);
        System.out.printf("token = %s\n",token);
    }


    @Test
    public void tokenToUserInfo() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoi5bCP5piOIiwiZXhwIjoxNTgyMDI2OTM4fQ.PQv3PbNjbuWitIMe9L24RhLBFN3PYAIh1SIVpfNsBMQASB17GY3vpn8a9l056bLrUvZ-jcPpEQtASIUjNzYZZyUgw27Q2c2lFEQZcMwqdiZQZBUMHtWnydysptVmMgAeHeNoTIqcsep4EjVVT4EAyaJr1wVnwY5Lcm7bcViKPHE";
        UserInfo userInfo = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.printf(" userInfo = %s\n", userInfo.toString());
    }

}
