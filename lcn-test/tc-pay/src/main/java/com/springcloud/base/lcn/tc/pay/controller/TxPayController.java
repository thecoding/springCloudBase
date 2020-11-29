package com.springcloud.base.lcn.tc.pay.controller;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.springcloud.base.lcn.tc.pay.entity.TxPay;
import com.springcloud.base.lcn.tc.pay.service.TxPayService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TxPay)表控制层
 *
 * @author makejava
 * @since 2020-11-21 00:29:54
 */
@RestController
@RequestMapping("/txPay")
public class TxPayController {
    /**
     * 服务对象
     */
    @Resource
    private TxPayService txPayService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TxPay selectOne(Long id) {
        return this.txPayService.queryById(id);
    }


    /**
     * 模拟正常保存
     * @param name
     * @return
     */
    @PostMapping("/insert")
    @Transactional(rollbackFor = Exception.class)
    @LcnTransaction
    public String insert(@RequestParam(value = "name",required = true) String name){
        TxPay txPay = new TxPay();
        txPay.setPayName("Pay:"+name);
        txPayService.insert(txPay);
        return null;
    }


    /**
     * 模拟保存到DB异常
     * @param name
     * @return
     */
    @PostMapping("/insert-error")
    @Transactional(rollbackFor = Exception.class)
    @LcnTransaction
    public String insertError(@RequestParam(value = "name",required = true) String name){
        TxPay txPay = new TxPay();
        txPay.setPayName("Pay:"+name);
        txPayService.insert(txPay);
        int i = 1/0;
        return null;
    }

    @PostMapping("/pay")
    public String test(){
        return "this is pay server";
    }


}