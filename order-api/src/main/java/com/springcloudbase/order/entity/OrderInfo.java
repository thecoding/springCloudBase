package com.springcloudbase.order.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (OrderInfo)实体类
 *
 * @author makejava
 * @since 2020-12-07 16:19:28
 */
public class OrderInfo implements Serializable {
    private static final long serialVersionUID = -57233507504065569L;
    
    private Long orderId;
    /**
    * 姓名
    */
    private String empName;
    /**
    * 商品名称
    */
    private String shopName;
    /**
    * 创建时间
    */
    private Date createTime;
    
    private Date updateTime;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}