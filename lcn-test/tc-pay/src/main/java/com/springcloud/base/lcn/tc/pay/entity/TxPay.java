package com.springcloud.base.lcn.tc.pay.entity;

import java.io.Serializable;

/**
 * (TxPay)实体类
 *
 * @author makejava
 * @since 2020-11-21 00:29:48
 */
public class TxPay implements Serializable {
    private static final long serialVersionUID = -80615891146577869L;
    
    private Long id;
    
    private String payName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

}