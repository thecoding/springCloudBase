package com.springcloud.base.lcn.tc.order.entity;

import java.io.Serializable;

/**
 * (TxOrder)实体类
 *
 * @author makejava
 * @since 2020-11-20 23:58:05
 */
public class TxOrder implements Serializable {
    private static final long serialVersionUID = 455166215479121901L;
    
    private Long id;
    /**
    * 订单名
    */
    private String orderName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

}