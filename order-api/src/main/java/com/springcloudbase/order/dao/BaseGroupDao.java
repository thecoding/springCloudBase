package com.springcloudbase.order.dao;

import com.springcloudbase.order.entity.BaseGroup;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (BaseGroup)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
public interface BaseGroupDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BaseGroup queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<BaseGroup> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param baseGroup 实例对象
     * @return 对象列表
     */
    List<BaseGroup> queryAll(BaseGroup baseGroup);

    /**
     * 新增数据
     *
     * @param baseGroup 实例对象
     * @return 影响行数
     */
    int insert(BaseGroup baseGroup);

    /**
     * 修改数据
     *
     * @param baseGroup 实例对象
     * @return 影响行数
     */
    int update(BaseGroup baseGroup);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}