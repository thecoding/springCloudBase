package com.springcloudbase.order.service.impl;

import com.springcloudbase.order.entity.BaseResourceAuthority;
import com.springcloudbase.order.dao.BaseResourceAuthorityDao;
import com.springcloudbase.order.service.BaseResourceAuthorityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (BaseResourceAuthority)表服务实现类
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
@Service("baseResourceAuthorityService")
public class BaseResourceAuthorityServiceImpl implements BaseResourceAuthorityService {
    @Resource
    private BaseResourceAuthorityDao baseResourceAuthorityDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public BaseResourceAuthority queryById(Integer id) {
        return this.baseResourceAuthorityDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<BaseResourceAuthority> queryAllByLimit(int offset, int limit) {
        return this.baseResourceAuthorityDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param baseResourceAuthority 实例对象
     * @return 实例对象
     */
    @Override
    public BaseResourceAuthority insert(BaseResourceAuthority baseResourceAuthority) {
        this.baseResourceAuthorityDao.insert(baseResourceAuthority);
        return baseResourceAuthority;
    }

    /**
     * 修改数据
     *
     * @param baseResourceAuthority 实例对象
     * @return 实例对象
     */
    @Override
    public BaseResourceAuthority update(BaseResourceAuthority baseResourceAuthority) {
        this.baseResourceAuthorityDao.update(baseResourceAuthority);
        return this.queryById(baseResourceAuthority.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.baseResourceAuthorityDao.deleteById(id) > 0;
    }
}