package com.springcloudbase.order.controller;

import com.springcloudbase.order.entity.AuthClient;
import com.springcloudbase.order.service.AuthClientService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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

}