package com.springcloudbase.order.controller;

import com.springcloudbase.order.entity.BaseElement;
import com.springcloudbase.order.service.BaseElementService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (BaseElement)表控制层
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
@RestController
@RequestMapping("baseElement")
public class BaseElementController {
    /**
     * 服务对象
     */
    @Resource
    private BaseElementService baseElementService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public BaseElement selectOne(Integer id) {
        return this.baseElementService.queryById(id);
    }

}