package com.springcloudbase.order.service.impl;

import com.springcloudbase.order.entity.AuthClient;
import com.springcloudbase.order.dao.AuthClientDao;
import com.springcloudbase.order.service.AuthClientService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (AuthClient)表服务实现类
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
@Service("authClientService")
public class AuthClientServiceImpl implements AuthClientService {
    @Resource
    private AuthClientDao authClientDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public AuthClient queryById(Integer id) {
        return this.authClientDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<AuthClient> queryAllByLimit(int offset, int limit) {
        return this.authClientDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param authClient 实例对象
     * @return 实例对象
     */
    @Override
    public AuthClient insert(AuthClient authClient) {
        this.authClientDao.insert(authClient);
        return authClient;
    }

    /**
     * 修改数据
     *
     * @param authClient 实例对象
     * @return 实例对象
     */
    @Override
    public AuthClient update(AuthClient authClient) {
        this.authClientDao.update(authClient);
        return this.queryById(authClient.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.authClientDao.deleteById(id) > 0;
    }
}