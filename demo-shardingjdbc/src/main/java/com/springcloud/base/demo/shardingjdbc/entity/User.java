package com.springcloud.base.demo.shardingjdbc.entity;

public class User {

    Long userId;
    String userName;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;

    }
}
