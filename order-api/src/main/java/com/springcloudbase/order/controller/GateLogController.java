package com.springcloudbase.order.controller;

import com.springcloudbase.order.entity.GateLog;
import com.springcloudbase.order.service.GateLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (GateLog)表控制层
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
@RestController
@RequestMapping("gateLog")
public class GateLogController {
    /**
     * 服务对象
     */
    @Resource
    private GateLogService gateLogService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public GateLog selectOne(Integer id) {
        return this.gateLogService.queryById(id);
    }

}