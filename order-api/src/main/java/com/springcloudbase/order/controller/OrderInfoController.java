package com.springcloudbase.order.controller;

import com.springcloudbase.order.entity.OrderInfo;
import com.springcloudbase.order.service.OrderInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (OrderInfo)表控制层
 *
 * @author makejava
 * @since 2020-12-07 16:19:40
 */
@RestController
@RequestMapping("orderInfo")
public class OrderInfoController {
    /**
     * 服务对象
     */
    @Resource
    private OrderInfoService orderInfoService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public OrderInfo selectOne(Long id) {
        return this.orderInfoService.queryById(id);
    }

}