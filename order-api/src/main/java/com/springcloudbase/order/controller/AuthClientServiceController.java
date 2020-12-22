package com.springcloudbase.order.controller;

import com.springcloudbase.order.entity.AuthClientService;
import com.springcloudbase.order.service.AuthClientServiceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (AuthClientService)表控制层
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
@RestController
@RequestMapping("authClientService")
public class AuthClientServiceController {
    /**
     * 服务对象
     */
    @Resource
    private AuthClientServiceService authClientServiceService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public AuthClientService selectOne(Integer id) {
        return this.authClientServiceService.queryById(id);
    }

}