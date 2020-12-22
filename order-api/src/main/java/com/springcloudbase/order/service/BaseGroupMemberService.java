package com.springcloudbase.order.service;

import com.springcloudbase.order.entity.BaseGroupMember;
import java.util.List;

/**
 * (BaseGroupMember)表服务接口
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
public interface BaseGroupMemberService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BaseGroupMember queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<BaseGroupMember> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param baseGroupMember 实例对象
     * @return 实例对象
     */
    BaseGroupMember insert(BaseGroupMember baseGroupMember);

    /**
     * 修改数据
     *
     * @param baseGroupMember 实例对象
     * @return 实例对象
     */
    BaseGroupMember update(BaseGroupMember baseGroupMember);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}