package com.springcloud.base.demo.shardingjdbc.service;

import com.springcloud.base.demo.shardingjdbc.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    public void processUsers() throws SQLException;

    public List<User> getUsers() throws SQLException;
}
