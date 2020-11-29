package com.springcloud.base.lcn.tc.order.controller;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.springcloud.base.lcn.tc.order.entity.TxOrder;
import com.springcloud.base.lcn.tc.order.service.TxOrderService;
import org.h2.util.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * (TxOrder)表控制层
 *
 * @author makejava
 * @since 2020-11-20 23:58:09
 */
@RestController
@RequestMapping("/txOrder-lcn")
public class LCNTxOrderController {
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
        return "success";
    }


    @GetMapping("/insert-pay-error")
    @Transactional(rollbackFor = Exception.class)
    @LcnTransaction
    public String insertToPayError(@RequestParam("name") String name){
        MultiValueMap map = new LinkedMultiValueMap();
        map.add("name", name);
        restTemplate.postForEntity("http://lcn:20001/txPay/insert", map, String.class);

        TxOrder txOrder = new TxOrder();
        txOrder.setOrderName(name);
        txOrderService.insert(txOrder);
        int i = 1/0;
        return "success";
    }

    @GetMapping("/insert-pay-ok")
    @Transactional(rollbackFor = Exception.class)
    @LcnTransaction
    public String insertToPayOk(@RequestParam("name") String name){
        MultiValueMap map = new LinkedMultiValueMap();
        map.add("name", name);
        restTemplate.postForEntity("http://lcn:20001/txPay/insert", map, String.class);

        TxOrder txOrder = new TxOrder();
        txOrder.setOrderName(name);
        txOrderService.insert(txOrder);
        return "success";
    }


    /**
     * 模拟请求，order服务正常，但是调用服务异常情况
     * @param name
     * @return
     */
    @GetMapping("/insert-pay-ok-error")
    @Transactional(rollbackFor = Exception.class)
    @LcnTransaction
    public String insertToPayOkError(@RequestParam("name") String name){
        MultiValueMap map = new LinkedMultiValueMap();
        map.add("name", name);
        restTemplate.postForEntity("http://lcn:20001/txPay/insert-error", map, String.class);

        TxOrder txOrder = new TxOrder();
        txOrder.setOrderName(name);
        txOrderService.insert(txOrder);
        return "success";
    }


}