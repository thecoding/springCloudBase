package com.springcloudbase.oauth2.service.impl;

import com.springcloudbase.oauth2.entity.User;
import com.springcloudbase.oauth2.provider.OrganizationProvider;
import com.springcloudbase.oauth2.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private OrganizationProvider organizationProvider;

    @Override
    public User getByUniqueId(String uniqueId) {
        return organizationProvider.getUserByUniqueId(uniqueId).getData();
    }
}
