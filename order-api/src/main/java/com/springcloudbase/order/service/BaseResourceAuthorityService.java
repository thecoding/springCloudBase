package com.springcloudbase.order.service;

import com.springcloudbase.order.entity.BaseResourceAuthority;
import java.util.List;

/**
 * (BaseResourceAuthority)表服务接口
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
public interface BaseResourceAuthorityService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BaseResourceAuthority queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<BaseResourceAuthority> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param baseResourceAuthority 实例对象
     * @return 实例对象
     */
    BaseResourceAuthority insert(BaseResourceAuthority baseResourceAuthority);

    /**
     * 修改数据
     *
     * @param baseResourceAuthority 实例对象
     * @return 实例对象
     */
    BaseResourceAuthority update(BaseResourceAuthority baseResourceAuthority);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}