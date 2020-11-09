package com.springcloud.base.zuul;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
@EnableZuulProxy
public class ZuulApplication {

    public static void main(String[] args) {
        initSentinelResource();
        SpringApplication.run(ZuulApplication.class, args);
    }


    /**
     * 初始化Sentinel
     */
    public static void initSentinelResource(){
        FlowRule rule = new FlowRule();
        //资源名称
        rule.setResource("ZuulFilterLimitController.sentinelSourceTest");
        //限流类型
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //限流数量
        rule.setCount(2);

        ArrayList<FlowRule> rules = new ArrayList<>();
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }


    /**
     * 开启SentinelResource切面
     * @return
     */
    @Bean
    public SentinelResourceAspect sentinelResourceAspect(){
        return new SentinelResourceAspect();
    }
}
