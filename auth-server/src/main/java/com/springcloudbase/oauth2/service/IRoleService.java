package com.springcloudbase.oauth2.service;

import com.springcloudbase.oauth2.entity.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface IRoleService {

    Set<Role> queryUserRolesByUserId(String userId);

}
