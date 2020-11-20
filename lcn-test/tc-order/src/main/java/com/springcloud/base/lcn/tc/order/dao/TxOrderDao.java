package com.springcloud.base.lcn.tc.order.dao;

import com.springcloud.base.lcn.tc.order.entity.TxOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TxOrder)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-20 23:58:07
 */
@Mapper
public interface TxOrderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TxOrder queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TxOrder> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param txOrder 实例对象
     * @return 对象列表
     */
    List<TxOrder> queryAll(TxOrder txOrder);

    /**
     * 新增数据
     *
     * @param txOrder 实例对象
     * @return 影响行数
     */
    int insert(TxOrder txOrder);

    /**
     * 修改数据
     *
     * @param txOrder 实例对象
     * @return 影响行数
     */
    int update(TxOrder txOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}