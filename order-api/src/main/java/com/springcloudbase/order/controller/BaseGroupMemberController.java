package com.springcloudbase.order.controller;

import com.springcloudbase.order.entity.BaseGroupMember;
import com.springcloudbase.order.service.BaseGroupMemberService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (BaseGroupMember)表控制层
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
@RestController
@RequestMapping("baseGroupMember")
public class BaseGroupMemberController {
    /**
     * 服务对象
     */
    @Resource
    private BaseGroupMemberService baseGroupMemberService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public BaseGroupMember selectOne(Integer id) {
        return this.baseGroupMemberService.queryById(id);
    }

}