package com.springcloud.base.lcn.tc.order.controller;

import com.springcloud.base.lcn.tc.order.entity.TxOrder;
import com.springcloud.base.lcn.tc.order.service.TxOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * (TxOrder)表控制层
 *
 * @author makejava
 * @since 2020-11-20 23:58:09
 */
@RestController
@RequestMapping("/txOrder")
public class TxOrderController {
    /**
     * 服务对象
     */
    @Resource
    private TxOrderService txOrderService;

    @Autowired
    RestTemplate restTemplate;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectOne")
    public TxOrder selectOne(Long id) {
        return this.txOrderService.queryById(id);
    }

    @GetMapping("/insert")
    @Transactional(rollbackFor = Exception.class)
    public String insert(@RequestParam("name") String name){
        TxOrder txOrder = new TxOrder();
        txOrder.setOrderName(name);
        txOrderService.insert(txOrder);
        int i = 1/0;
        return "success";
    }

    @GetMapping("/toPay")

    @Transactional(rollbackFor = Exception.class)
    public String testPay(@RequestParam("name") String name){
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://lcn:20001/txPay/pay", null, String.class);
        return stringResponseEntity.getBody();
    }

}