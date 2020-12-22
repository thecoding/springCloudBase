package com.springcloudbase.order.service.impl;

import com.springcloudbase.order.entity.GateLog;
import com.springcloudbase.order.dao.GateLogDao;
import com.springcloudbase.order.service.GateLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (GateLog)表服务实现类
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
@Service("gateLogService")
public class GateLogServiceImpl implements GateLogService {
    @Resource
    private GateLogDao gateLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public GateLog queryById(Integer id) {
        return this.gateLogDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<GateLog> queryAllByLimit(int offset, int limit) {
        return this.gateLogDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param gateLog 实例对象
     * @return 实例对象
     */
    @Override
    public GateLog insert(GateLog gateLog) {
        this.gateLogDao.insert(gateLog);
        return gateLog;
    }

    /**
     * 修改数据
     *
     * @param gateLog 实例对象
     * @return 实例对象
     */
    @Override
    public GateLog update(GateLog gateLog) {
        this.gateLogDao.update(gateLog);
        return this.queryById(gateLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.gateLogDao.deleteById(id) > 0;
    }
}