package com.springcloudbase.order.controller;

import com.springcloudbase.order.entity.BaseGroup;
import com.springcloudbase.order.service.BaseGroupService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (BaseGroup)表控制层
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
@RestController
@RequestMapping("baseGroup")
public class BaseGroupController {
    /**
     * 服务对象
     */
    @Resource
    private BaseGroupService baseGroupService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public BaseGroup selectOne(Integer id) {
        return this.baseGroupService.queryById(id);
    }

}