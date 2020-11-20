package com.springcloud.base.lcn.tc.pay.dao;

import com.springcloud.base.lcn.tc.pay.entity.TxPay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TxPay)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-21 00:29:52
 */
@Mapper
public interface TxPayDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TxPay queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TxPay> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param txPay 实例对象
     * @return 对象列表
     */
    List<TxPay> queryAll(TxPay txPay);

    /**
     * 新增数据
     *
     * @param txPay 实例对象
     * @return 影响行数
     */
    int insert(TxPay txPay);

    /**
     * 修改数据
     *
     * @param txPay 实例对象
     * @return 影响行数
     */
    int update(TxPay txPay);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}