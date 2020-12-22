package com.springcloudbase.order.service;

import com.springcloudbase.order.entity.AuthClientService;
import java.util.List;

/**
 * (AuthClientService)表服务接口
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
public interface AuthClientServiceService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AuthClientService queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AuthClientService> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param authClientService 实例对象
     * @return 实例对象
     */
    AuthClientService insert(AuthClientService authClientService);

    /**
     * 修改数据
     *
     * @param authClientService 实例对象
     * @return 实例对象
     */
    AuthClientService update(AuthClientService authClientService);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}