package com.springcloud.base.lcn.tc.pay.model;

import java.io.Serializable;
import lombok.Data;

/**
 * tx_pay
 * @author 
 */
@Data
public class TxPay implements Serializable {
    private Long id;

    private String payName;

    private static final long serialVersionUID = 1L;
}