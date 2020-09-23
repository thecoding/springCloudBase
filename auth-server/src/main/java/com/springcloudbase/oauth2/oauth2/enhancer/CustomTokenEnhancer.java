package com.springcloudbase.oauth2.oauth2.enhancer;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;


/**
 * 自定义token增强，可以往这里面增加自定义的属性
 */
public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Map<String, Object> customMapInfo = new HashMap<>();
        customMapInfo.put("info", "info");
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(customMapInfo);
        return accessToken;
    }
}
