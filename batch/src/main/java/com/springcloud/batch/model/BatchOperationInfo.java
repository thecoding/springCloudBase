package com.springcloud.batch.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Mirko
 * @Description
 * @createTime 2020年06月09日 00:19:00
 */
public class BatchOperationInfo implements Serializable {

    //跑批类型
    private int batchType;
    //备注
    private String batchRemark;
    //跑批数量
    private String batchNum;
    //跑批标识
    private String batchFlowId;
    //具体参数
    private List<BatchOperationData> dataList;


    public String getBatchFlowId() {
        return batchFlowId;
    }

    public void setBatchFlowId(String batchFlowId) {
        this.batchFlowId = batchFlowId;
    }

    public int getBatchType() {
        return batchType;
    }

    public void setBatchType(int batchType) {
        this.batchType = batchType;
    }

    public String getBatchRemark() {
        return batchRemark;
    }

    public void setBatchRemark(String batchRemark) {
        this.batchRemark = batchRemark;
    }

    public String getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum;
    }

    public List<BatchOperationData> getDataList() {
        return dataList;
    }

    public void setDataList(List<BatchOperationData> dataList) {
        this.dataList = dataList;
    }
}
