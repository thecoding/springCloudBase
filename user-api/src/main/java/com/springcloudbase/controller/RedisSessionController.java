package com.springcloudbase.controller;

import com.springcloudbase.annotation.Token;
import com.springcloudbase.redis.RedisUtil;
import com.springcloudbase.util.SessionUtil;
import com.springcloudbase.vo.BaseUser;
import com.springcloudbase.vo.UserDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 *
 *  测试 redis session
 * Created by Mirko on 2020/4/12.
 */
@RestController
@RequestMapping("/redis")
public class RedisSessionController {



    @Autowired
    RedisTemplate redisTemplate;

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

    @RequestMapping("/save")
    public Object setObject(@RequestParam(defaultValue = "15") Integer db){
        UserDataInfo userDataInfo = new UserDataInfo();
        userDataInfo.setId(234);
        userDataInfo.setName("test");
        RedisUtil.getInstance().setRedisTemplate(redisTemplate,db).set("test2", userDataInfo);
        return true;
    }

    @RequestMapping("/get")
    public UserDataInfo getObject(@RequestParam(defaultValue = "15") Integer db){
        return (UserDataInfo) RedisUtil.getInstance().setRedisTemplate(redisTemplate,db).get("test2");
    }


    @RequestMapping("/tokenTest")
    @Token
    public String tokenTest(){
        return "token";
    }

    /************redis 尝试切换db ******************/


}
