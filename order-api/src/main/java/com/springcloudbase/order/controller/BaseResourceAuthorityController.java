package com.springcloudbase.order.controller;

import com.springcloudbase.order.entity.BaseResourceAuthority;
import com.springcloudbase.order.service.BaseResourceAuthorityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (BaseResourceAuthority)表控制层
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
@RestController
@RequestMapping("baseResourceAuthority")
public class BaseResourceAuthorityController {
    /**
     * 服务对象
     */
    @Resource
    private BaseResourceAuthorityService baseResourceAuthorityService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public BaseResourceAuthority selectOne(Integer id) {
        return this.baseResourceAuthorityService.queryById(id);
    }

}