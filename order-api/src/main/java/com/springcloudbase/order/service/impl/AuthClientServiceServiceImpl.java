package com.springcloudbase.order.service.impl;

import com.springcloudbase.order.entity.AuthClientService;
import com.springcloudbase.order.dao.AuthClientServiceDao;
import com.springcloudbase.order.service.AuthClientServiceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (AuthClientService)表服务实现类
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
@Service("authClientServiceService")
public class AuthClientServiceServiceImpl implements AuthClientServiceService {
    @Resource
    private AuthClientServiceDao authClientServiceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthClientService queryById(Integer id) {
        return this.authClientServiceDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<AuthClientService> queryAllByLimit(int offset, int limit) {
        return this.authClientServiceDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param authClientService 实例对象
     * @return 实例对象
     */
    @Override
    public AuthClientService insert(AuthClientService authClientService) {
        this.authClientServiceDao.insert(authClientService);
        return authClientService;
    }

    /**
     * 修改数据
     *
     * @param authClientService 实例对象
     * @return 实例对象
     */
    @Override
    public AuthClientService update(AuthClientService authClientService) {
        this.authClientServiceDao.update(authClientService);
        return this.queryById(authClientService.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.authClientServiceDao.deleteById(id) > 0;
    }
}