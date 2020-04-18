package com.springcloudbase.controller;

import com.springcloudbase.annotation.Token;
import com.springcloudbase.redis.RedisUtil;
import com.springcloudbase.util.SessionUtil;
import com.springcloudbase.vo.BaseUser;
import com.springcloudbase.vo.UserDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *  测试 redis session
 * Created by Mirko on 2020/4/12.
 */
@RestController
public class RedisSessionController {

    @Autowired
    RedisUtil redisUtil;

    @RequestMapping("/userId")
    public BaseUser createBaseUser(@RequestParam("userId") long userId){
        BaseUser baseUser = new BaseUser(userId, "小李");
        SessionUtil.setUser(baseUser);
        return baseUser;
    }

    @RequestMapping("/myself")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public BaseUser getUserInfo() {
        return SessionUtil.getUser();
    }

    @RequestMapping("/loginOut")
    public boolean loginOut(){
        SessionUtil.delUser();
        return true;
    }


    /************redis 测试******************/

    @RequestMapping("/redisSaveObject")
    public Object setObject(){
        UserDataInfo userDataInfo = new UserDataInfo();
        userDataInfo.setId(234);
        userDataInfo.setName("test");
        redisUtil.set("test2", userDataInfo);
        int i = 1/0;
        return true;
    }

    @RequestMapping("/redisGetObject")
    public UserDataInfo getObject(){
        return (UserDataInfo) redisUtil.get("test1");
    }


    @RequestMapping("/tokenTest")
    @Token
    public String tokenTest(){
        return "token";
    }
}
