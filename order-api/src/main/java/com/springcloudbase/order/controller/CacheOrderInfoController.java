package com.springcloudbase.order.controller;

import com.springcloudbase.order.entity.OrderInfo;
import com.springcloudbase.order.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (OrderInfo)表控制层
 *
 * @author makejava
 * @since 2020-12-07 16:19:40
 */
@RestController
@RequestMapping("orderInfo")
public class CacheOrderInfoController {
    /**
     * 服务对象
     */
    @Resource
    private OrderInfoService orderInfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public OrderInfo selectOne(Long id) {
        return this.orderInfoService.queryById(id);
    }


    /**
     * 增加缓存
     * value = {"order","user"},key = "#root.methodName", sync = true
     * sync=true  只支持单一个缓存，就是value不能配置多个组
     * @return
     */
    @Cacheable(value = {"order"},key = "#root.methodName", sync = true)
    @GetMapping("/testCache")
    public String testCache(@RequestParam("name") String name){
        return "testCache-string" + name;
    }


    /**
     * 清除缓存
     * @return
     */
    @CacheEvict(value = {"order"}, allEntries = true)
    @GetMapping("/cleanCache")
    public String cleanTestCache(){
        return "clean success";
    }


    @Autowired
    CacheManager cacheManager;

    /**
     * 手动获取缓存中的值
     * @param cacheName
     * @param key
     * @return
     */
    @GetMapping("/getCache")
    public String getCache(@RequestParam("cacheName") String cacheName,@RequestParam("key") String key){
        Cache cache = cacheManager.getCache(cacheName);
        String string = cache.get(key, String.class);
        return string;
    }
}