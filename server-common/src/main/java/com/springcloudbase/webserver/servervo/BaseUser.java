package com.springcloudbase.webserver.servervo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Mirko on 2020/4/11.
 */
@Data
public class BaseUser implements Serializable {

    private String name;
    private long userId;

    public BaseUser() {
    }

    public BaseUser(long userId,String name) {
        this.name = name;
        this.userId = userId;
    }

}
