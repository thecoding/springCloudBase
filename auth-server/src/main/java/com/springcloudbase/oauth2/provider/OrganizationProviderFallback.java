package com.springcloudbase.oauth2.provider;

import com.springcloudbase.base.vo.Result;
import com.springcloudbase.oauth2.entity.Role;
import com.springcloudbase.oauth2.entity.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class OrganizationProviderFallback implements OrganizationProvider {

    @Override
    public Result<User> getUserByUniqueId(String uniqueId) {
        return Result.success(new User());
    }

    @Override
    public Result<Set<Role>> queryRolesByUserId(String userId) {
        return Result.success(new HashSet<Role>());
    }
}
