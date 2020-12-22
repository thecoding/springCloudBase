package com.springcloudbase.order.service.impl;

import com.springcloudbase.order.entity.BaseGroupType;
import com.springcloudbase.order.dao.BaseGroupTypeDao;
import com.springcloudbase.order.service.BaseGroupTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (BaseGroupType)表服务实现类
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
@Service("baseGroupTypeService")
public class BaseGroupTypeServiceImpl implements BaseGroupTypeService {
    @Resource
    private BaseGroupTypeDao baseGroupTypeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BaseGroupType queryById(Integer id) {
        return this.baseGroupTypeDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<BaseGroupType> queryAllByLimit(int offset, int limit) {
        return this.baseGroupTypeDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param baseGroupType 实例对象
     * @return 实例对象
     */
    @Override
    public BaseGroupType insert(BaseGroupType baseGroupType) {
        this.baseGroupTypeDao.insert(baseGroupType);
        return baseGroupType;
    }

    /**
     * 修改数据
     *
     * @param baseGroupType 实例对象
     * @return 实例对象
     */
    @Override
    public BaseGroupType update(BaseGroupType baseGroupType) {
        this.baseGroupTypeDao.update(baseGroupType);
        return this.queryById(baseGroupType.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.baseGroupTypeDao.deleteById(id) > 0;
    }
}