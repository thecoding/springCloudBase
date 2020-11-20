package com.springcloud.base.lcn.tc.order.service.impl;

import com.springcloud.base.lcn.tc.order.entity.TxOrder;
import com.springcloud.base.lcn.tc.order.dao.TxOrderDao;
import com.springcloud.base.lcn.tc.order.service.TxOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TxOrder)表服务实现类
 *
 * @author makejava
 * @since 2020-11-20 23:58:08
 */
@Service("txOrderService")
public class TxOrderServiceImpl implements TxOrderService {
    @Resource
    private TxOrderDao txOrderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TxOrder queryById(Long id) {
        return this.txOrderDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TxOrder> queryAllByLimit(int offset, int limit) {
        return this.txOrderDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param txOrder 实例对象
     * @return 实例对象
     */
    @Override
    public TxOrder insert(TxOrder txOrder) {
        this.txOrderDao.insert(txOrder);
        return txOrder;
    }

    /**
     * 修改数据
     *
     * @param txOrder 实例对象
     * @return 实例对象
     */
    @Override
    public TxOrder update(TxOrder txOrder) {
        this.txOrderDao.update(txOrder);
        return this.queryById(txOrder.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.txOrderDao.deleteById(id) > 0;
    }
}