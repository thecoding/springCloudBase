package com.springcloud.base.lcn.tc.order.dao;

import com.springcloud.base.lcn.tc.order.model.TxOrder;

public interface TxOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(TxOrder record);

    int insertSelective(TxOrder record);

    TxOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TxOrder record);

    int updateByPrimaryKey(TxOrder record);
}