package com.springcloudbase.base.vo;

import java.io.Serializable;

/**
 * @author Mirko
 * @Description
 * @createTime 2020年02月19日 00:10:00
 */
public class UserDataInfo implements Serializable {

    private long id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
