package com.springcloud.base.lcn.tc.order.model;

import java.io.Serializable;
import lombok.Data;

/**
 * tx_order
 * @author 
 */
@Data
public class TxOrder implements Serializable {
    private Long id;

    /**
     * 订单名
     */
    private String orderName;

    private static final long serialVersionUID = 1L;
}