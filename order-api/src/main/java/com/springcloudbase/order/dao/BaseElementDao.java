package com.springcloudbase.order.dao;

import com.springcloudbase.order.entity.BaseElement;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (BaseElement)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
public interface BaseElementDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    BaseElement queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<BaseElement> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param baseElement 实例对象
     * @return 对象列表
     */
    List<BaseElement> queryAll(BaseElement baseElement);

    /**
     * 新增数据
     *
     * @param baseElement 实例对象
     * @return 影响行数
     */
    int insert(BaseElement baseElement);

    /**
     * 修改数据
     *
     * @param baseElement 实例对象
     * @return 影响行数
     */
    int update(BaseElement baseElement);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}