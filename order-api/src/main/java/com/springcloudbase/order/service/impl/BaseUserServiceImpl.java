package com.springcloudbase.order.service.impl;

import com.springcloudbase.order.entity.BaseUser;
import com.springcloudbase.order.dao.BaseUserDao;
import com.springcloudbase.order.service.BaseUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (BaseUser)表服务实现类
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
@Service("baseUserService")
public class BaseUserServiceImpl implements BaseUserService {
    @Resource
    private BaseUserDao baseUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BaseUser queryById(Integer id) {
        return this.baseUserDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<BaseUser> queryAllByLimit(int offset, int limit) {
        return this.baseUserDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param baseUser 实例对象
     * @return 实例对象
     */
    @Override
    public BaseUser insert(BaseUser baseUser) {
        this.baseUserDao.insert(baseUser);
        return baseUser;
    }

    /**
     * 修改数据
     *
     * @param baseUser 实例对象
     * @return 实例对象
     */
    @Override
    public BaseUser update(BaseUser baseUser) {
        this.baseUserDao.update(baseUser);
        return this.queryById(baseUser.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.baseUserDao.deleteById(id) > 0;
    }
}