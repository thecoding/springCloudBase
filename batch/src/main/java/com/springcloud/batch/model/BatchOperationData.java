package com.springcloud.batch.model;

import java.io.Serializable;

/**
 * @author Mirko
 * @Description 具体执行的参数
 * @createTime 2020年06月09日 00:22:00
 */
public class BatchOperationData implements Serializable, Comparable<BatchOperationData>{

    //键
    private String key;
    //值
    private Object value;
    //顺序
    private int seq;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    @Override
    public int compareTo(BatchOperationData o) {
        return seq >= o.getSeq() ? -1 : 1;
    }
}
