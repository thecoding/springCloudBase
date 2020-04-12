package com.springcloudbase.filter;

/**
 * Created by Mirko on 2020/4/11.
 */
public interface BaseFilter extends Comparable<BaseFilter> {

    /**
     * 目标方法执行前执行
     * @return boolean
     */
    boolean preExecute();

    /**
     * 目标方法执行后调用
     */
    void afterExecute();

    /**
     * 排序
     * @return int
     */
    int order();

    /**
     * @desc 是否执行（默认为false）
     * @return boolean
     */
    default  boolean isMustExecute() {
        return false;
    }
}
