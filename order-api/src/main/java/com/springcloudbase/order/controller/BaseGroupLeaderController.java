package com.springcloudbase.order.controller;

import com.springcloudbase.order.entity.BaseGroupLeader;
import com.springcloudbase.order.service.BaseGroupLeaderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (BaseGroupLeader)表控制层
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
@RestController
@RequestMapping("baseGroupLeader")
public class BaseGroupLeaderController {
    /**
     * 服务对象
     */
    @Resource
    private BaseGroupLeaderService baseGroupLeaderService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public BaseGroupLeader selectOne(Integer id) {
        return this.baseGroupLeaderService.queryById(id);
    }

}