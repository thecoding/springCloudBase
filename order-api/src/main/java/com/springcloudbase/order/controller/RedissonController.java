package com.springcloudbase.order.controller;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Mirko
 * @Description
 * @createTime 2020年12月13日 14:28:00
 */
@RestController
@RequestMapping("redis")
public class RedissonController {

    @Autowired
    RedissonClient redissonClient;

    @RequestMapping("/getLock")
    public String getLock() throws InterruptedException {
        RLock lock = redissonClient.getLock("redis-lock");
        //锁只保持10秒钟，超过10秒自动清空，如果try中的业务超过10秒就会出现问题，正常tryLock的时间必须要超过业务执行时间
        lock.lock(10, TimeUnit.SECONDS);
//        lock.lock("");
        try {
            System.out.println("获取到了锁..");
            TimeUnit.SECONDS.sleep(30);
        } catch (Exception e) {

        } finally {
            System.out.println("释放锁");
            lock.unlock();
        }
        return "invoke-success";
    }

    @RequestMapping("/getLock2")
    public String getLock2() throws InterruptedException {
        RLock lock = redissonClient.getLock("redis-lock");
        //如果不加时间，lock会自动续期
        lock.lock();
        try {
            System.out.println("获取到了锁..");
            TimeUnit.SECONDS.sleep(30);
        } catch (Exception e) {

        } finally {
            System.out.println("释放锁");
            lock.unlock();
        }
        return "invoke-success";
    }
}
