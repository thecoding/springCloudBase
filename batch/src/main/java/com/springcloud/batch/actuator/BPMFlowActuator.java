package com.springcloud.batch.actuator;

import com.springcloud.batch.model.BatchOperationInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Mirko
 * @Description BPM执行器
 * @createTime 2020年06月09日 00:12:00
 */
public class BPMFlowActuator {

    @Autowired
    BPMFlowLocate bpmFlowLocate;


    void startFlow(BatchOperationInfo info) {

    }
}
