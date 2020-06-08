package com.springcloud.batch.actuator;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mirko
 * @Description flow流程定位器
 * @createTime 2020年06月09日 00:13:00
 */
@Component(value = "bmpFlowLocate")
public class BPMFlowLocate {

    private Map<String, String> map = new HashMap<>();

    //初始化的时候需要获取所有跑批构建器，并且注册到BMP注册器中
    @PostConstruct
    public void initLocate() {

    }





}
