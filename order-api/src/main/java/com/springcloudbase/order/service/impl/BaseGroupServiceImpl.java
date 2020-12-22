package com.springcloudbase.order.service.impl;

import com.springcloudbase.order.entity.BaseGroup;
import com.springcloudbase.order.dao.BaseGroupDao;
import com.springcloudbase.order.service.BaseGroupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (BaseGroup)表服务实现类
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
@Service("baseGroupService")
public class BaseGroupServiceImpl implements BaseGroupService {
    @Resource
    private BaseGroupDao baseGroupDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BaseGroup queryById(Integer id) {
        return this.baseGroupDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<BaseGroup> queryAllByLimit(int offset, int limit) {
        return this.baseGroupDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param baseGroup 实例对象
     * @return 实例对象
     */
    @Override
    public BaseGroup insert(BaseGroup baseGroup) {
        this.baseGroupDao.insert(baseGroup);
        return baseGroup;
    }

    /**
     * 修改数据
     *
     * @param baseGroup 实例对象
     * @return 实例对象
     */
    @Override
    public BaseGroup update(BaseGroup baseGroup) {
        this.baseGroupDao.update(baseGroup);
        return this.queryById(baseGroup.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.baseGroupDao.deleteById(id) > 0;
    }
}