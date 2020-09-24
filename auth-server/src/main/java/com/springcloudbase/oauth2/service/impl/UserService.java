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
//        return organizationProvider.getUserByUniqueId(uniqueId).getData();
        User user = new User();
        user.setMobile("1851111222");
        user.setUsername("testName");
        user.setPassword("$2a$10$2szDKjvKHJCWE6YQNznogOeQF3USZHmCYj1fG7YbfK.vnTgNKLzri");
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);
        return user;
    }
}
