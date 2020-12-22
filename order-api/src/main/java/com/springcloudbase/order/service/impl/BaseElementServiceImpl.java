package com.springcloudbase.order.service.impl;

import com.springcloudbase.order.entity.BaseElement;
import com.springcloudbase.order.dao.BaseElementDao;
import com.springcloudbase.order.service.BaseElementService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (BaseElement)表服务实现类
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
@Service("baseElementService")
public class BaseElementServiceImpl implements BaseElementService {
    @Resource
    private BaseElementDao baseElementDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BaseElement queryById(Integer id) {
        return this.baseElementDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<BaseElement> queryAllByLimit(int offset, int limit) {
        return this.baseElementDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param baseElement 实例对象
     * @return 实例对象
     */
    @Override
    public BaseElement insert(BaseElement baseElement) {
        this.baseElementDao.insert(baseElement);
        return baseElement;
    }

    /**
     * 修改数据
     *
     * @param baseElement 实例对象
     * @return 实例对象
     */
    @Override
    public BaseElement update(BaseElement baseElement) {
        this.baseElementDao.update(baseElement);
        return this.queryById(baseElement.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.baseElementDao.deleteById(id) > 0;
    }
}