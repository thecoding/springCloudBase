package com.springcloudbase.order.controller;

import com.springcloudbase.order.entity.BaseUser;
import com.springcloudbase.order.service.BaseUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (BaseUser)表控制层
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
@RestController
@RequestMapping("baseUser")
public class BaseUserController {
    /**
     * 服务对象
     */
    @Resource
    private BaseUserService baseUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public BaseUser selectOne(Integer id) {
        return this.baseUserService.queryById(id);
    }

}