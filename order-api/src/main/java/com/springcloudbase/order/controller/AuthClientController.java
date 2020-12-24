package com.springcloudbase.order.controller;

import com.github.pagehelper.PageInfo;
import com.springcloudbase.order.entity.AuthClient;
import com.springcloudbase.order.service.AuthClientService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * (AuthClient)表控制层
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
@RestController
@RequestMapping("authClient")
public class AuthClientController {
    /**
     * 服务对象
     */
    @Resource
    private AuthClientService authClientService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public AuthClient selectOne(Integer id) {
        return this.authClientService.queryById(id);
    }

    @GetMapping("selectPage")
    public PageInfo<AuthClient> getPageAuthClient() {
        PageInfo<AuthClient> pageAuthClient = authClientService.getPageAuthClient();
        return pageAuthClient;
    }


    //支持sql子查询中包含参数，并且能顺利分页
    @GetMapping("selectPage2")
    public PageInfo<Map<String,String>> getPageAuthClient1() {
        PageInfo<Map<String, String>> pageAuthClient1 = this.authClientService.getPageAuthClient1();
        return pageAuthClient1;
    }

}