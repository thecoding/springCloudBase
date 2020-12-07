package com.springcloudbase.order.service.impl;

import com.springcloudbase.order.entity.OrderInfo;
import com.springcloudbase.order.dao.OrderInfoDao;
import com.springcloudbase.order.service.OrderInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (OrderInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-12-07 16:19:39
 */
@Service("orderInfoService")
public class OrderInfoServiceImpl implements OrderInfoService {
    @Resource
    private OrderInfoDao orderInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param orderId 主键
     * @return 实例对象
     */
    @Override
    public OrderInfo queryById(Long orderId) {
        return this.orderInfoDao.queryById(orderId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<OrderInfo> queryAllByLimit(int offset, int limit) {
        return this.orderInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param orderInfo 实例对象
     * @return 实例对象
     */
    @Override
    public OrderInfo insert(OrderInfo orderInfo) {
        this.orderInfoDao.insert(orderInfo);
        return orderInfo;
    }

    /**
     * 修改数据
     *
     * @param orderInfo 实例对象
     * @return 实例对象
     */
    @Override
    public OrderInfo update(OrderInfo orderInfo) {
        this.orderInfoDao.update(orderInfo);
        return this.queryById(orderInfo.getOrderId());
    }

    /**
     * 通过主键删除数据
     *
     * @param orderId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long orderId) {
        return this.orderInfoDao.deleteById(orderId) > 0;
    }
}