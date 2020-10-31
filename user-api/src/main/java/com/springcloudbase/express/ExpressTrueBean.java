package com.springcloudbase.express;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Mirko
 * @Description
 * @createTime 2020年10月24日 01:08:00
 */
@Getter
@ToString
public class ExpressTrueBean {
    String name;

    public ExpressTrueBean(String name) {
        this.name = name;
    }

}
