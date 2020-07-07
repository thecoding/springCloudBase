package com.springcloudbase.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mirko on 2020/4/12.
 */
@Service
public class UserService {

    public String getUserName(){
        return "user service";
    }
}
