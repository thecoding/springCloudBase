package com.springcloudbase.oauth2.service.impl;


import com.springcloudbase.oauth2.entity.Role;
import com.springcloudbase.oauth2.provider.OrganizationProvider;
import com.springcloudbase.oauth2.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private OrganizationProvider organizationProvider;

    @Override
    public Set<Role> queryUserRolesByUserId(String userId) {
        return organizationProvider.queryRolesByUserId(userId).getData();
    }

}
