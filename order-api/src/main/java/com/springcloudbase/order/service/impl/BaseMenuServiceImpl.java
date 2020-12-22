package com.springcloudbase.order.service.impl;

import com.springcloudbase.order.entity.BaseMenu;
import com.springcloudbase.order.dao.BaseMenuDao;
import com.springcloudbase.order.service.BaseMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (BaseMenu)表服务实现类
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
@Service("baseMenuService")
public class BaseMenuServiceImpl implements BaseMenuService {
    @Resource
    private BaseMenuDao baseMenuDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BaseMenu queryById(Integer id) {
        return this.baseMenuDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<BaseMenu> queryAllByLimit(int offset, int limit) {
        return this.baseMenuDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param baseMenu 实例对象
     * @return 实例对象
     */
    @Override
    public BaseMenu insert(BaseMenu baseMenu) {
        this.baseMenuDao.insert(baseMenu);
        return baseMenu;
    }

    /**
     * 修改数据
     *
     * @param baseMenu 实例对象
     * @return 实例对象
     */
    @Override
    public BaseMenu update(BaseMenu baseMenu) {
        this.baseMenuDao.update(baseMenu);
        return this.queryById(baseMenu.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.baseMenuDao.deleteById(id) > 0;
    }
}