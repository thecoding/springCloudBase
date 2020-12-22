package com.springcloudbase.order.controller;

import com.springcloudbase.order.entity.BaseGroupType;
import com.springcloudbase.order.service.BaseGroupTypeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (BaseGroupType)表控制层
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
@RestController
@RequestMapping("baseGroupType")
public class BaseGroupTypeController {
    /**
     * 服务对象
     */
    @Resource
    private BaseGroupTypeService baseGroupTypeService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public BaseGroupType selectOne(Integer id) {
        return this.baseGroupTypeService.queryById(id);
    }

}