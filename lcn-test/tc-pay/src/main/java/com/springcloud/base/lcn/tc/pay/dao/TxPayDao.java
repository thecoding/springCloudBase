package com.springcloud.base.lcn.tc.pay.dao;

import com.springcloud.base.lcn.tc.pay.model.TxPay;

public interface TxPayDao {
    int deleteByPrimaryKey(Long id);

    int insert(TxPay record);

    int insertSelective(TxPay record);

    TxPay selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TxPay record);

    int updateByPrimaryKey(TxPay record);
}