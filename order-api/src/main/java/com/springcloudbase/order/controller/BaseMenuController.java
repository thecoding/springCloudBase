package com.springcloudbase.order.controller;

import com.springcloudbase.order.entity.BaseMenu;
import com.springcloudbase.order.service.BaseMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (BaseMenu)表控制层
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
@RestController
@RequestMapping("baseMenu")
public class BaseMenuController {
    /**
     * 服务对象
     */
    @Resource
    private BaseMenuService baseMenuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public BaseMenu selectOne(Integer id) {
        return this.baseMenuService.queryById(id);
    }

}