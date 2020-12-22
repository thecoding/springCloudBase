package com.springcloudbase.order.service.impl;

import com.springcloudbase.order.entity.BaseGroupLeader;
import com.springcloudbase.order.dao.BaseGroupLeaderDao;
import com.springcloudbase.order.service.BaseGroupLeaderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (BaseGroupLeader)表服务实现类
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
@Service("baseGroupLeaderService")
public class BaseGroupLeaderServiceImpl implements BaseGroupLeaderService {
    @Resource
    private BaseGroupLeaderDao baseGroupLeaderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BaseGroupLeader queryById(Integer id) {
        return this.baseGroupLeaderDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<BaseGroupLeader> queryAllByLimit(int offset, int limit) {
        return this.baseGroupLeaderDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param baseGroupLeader 实例对象
     * @return 实例对象
     */
    @Override
    public BaseGroupLeader insert(BaseGroupLeader baseGroupLeader) {
        this.baseGroupLeaderDao.insert(baseGroupLeader);
        return baseGroupLeader;
    }

    /**
     * 修改数据
     *
     * @param baseGroupLeader 实例对象
     * @return 实例对象
     */
    @Override
    public BaseGroupLeader update(BaseGroupLeader baseGroupLeader) {
        this.baseGroupLeaderDao.update(baseGroupLeader);
        return this.queryById(baseGroupLeader.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.baseGroupLeaderDao.deleteById(id) > 0;
    }
}