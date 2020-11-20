package com.springcloud.base.lcn.tc.pay.service.impl;

import com.springcloud.base.lcn.tc.pay.entity.TxPay;
import com.springcloud.base.lcn.tc.pay.dao.TxPayDao;
import com.springcloud.base.lcn.tc.pay.service.TxPayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TxPay)表服务实现类
 *
 * @author makejava
 * @since 2020-11-21 00:29:54
 */
@Service("txPayService")
public class TxPayServiceImpl implements TxPayService {
    @Resource
    private TxPayDao txPayDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TxPay queryById(Long id) {
        return this.txPayDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TxPay> queryAllByLimit(int offset, int limit) {
        return this.txPayDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param txPay 实例对象
     * @return 实例对象
     */
    @Override
    public TxPay insert(TxPay txPay) {
        this.txPayDao.insert(txPay);
        return txPay;
    }

    /**
     * 修改数据
     *
     * @param txPay 实例对象
     * @return 实例对象
     */
    @Override
    public TxPay update(TxPay txPay) {
        this.txPayDao.update(txPay);
        return this.queryById(txPay.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.txPayDao.deleteById(id) > 0;
    }
}