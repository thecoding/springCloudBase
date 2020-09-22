package com.springcloudbase.webserver.oauth2;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

/**
 * @author Mirko
 * @Description
 * @createTime 2020年09月20日 00:58:00
 */
public class CustomUserDetails implements UserDetailsService {



    /**
     * 返回UserDetails信息
     * @param uniqueId
     * @return
     * @throws ClientRegistrationException
     */
    @Override
    public UserDetails loadUserByUsername(String uniqueId) throws UsernameNotFoundException {
        return null;
    }
}
