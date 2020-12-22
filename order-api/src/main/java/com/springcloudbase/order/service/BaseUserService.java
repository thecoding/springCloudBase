package com.springcloudbase.order.service;

import com.springcloudbase.order.entity.BaseUser;
import java.util.List;

/**
 * (BaseUser)表服务接口
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
public interface BaseUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BaseUser queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<BaseUser> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param baseUser 实例对象
     * @return 实例对象
     */
    BaseUser insert(BaseUser baseUser);

    /**
     * 修改数据
     *
     * @param baseUser 实例对象
     * @return 实例对象
     */
    BaseUser update(BaseUser baseUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}