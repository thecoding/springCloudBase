package com.springcloud.base.lcn.tc.order.service;

import com.springcloud.base.lcn.tc.order.entity.TxOrder;
import java.util.List;

/**
 * (TxOrder)表服务接口
 *
 * @author makejava
 * @since 2020-11-20 23:58:08
 */
public interface TxOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TxOrder queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TxOrder> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param txOrder 实例对象
     * @return 实例对象
     */
    TxOrder insert(TxOrder txOrder);

    /**
     * 修改数据
     *
     * @param txOrder 实例对象
     * @return 实例对象
     */
    TxOrder update(TxOrder txOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}