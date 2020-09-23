package com.springcloudbase.oauth2.config;

import com.springcloudbase.oauth2.exception.CustomWebResponseExceptionTranslator;
import com.springcloudbase.oauth2.oauth2.enhancer.CustomTokenEnhancer;
import com.springcloudbase.oauth2.oauth2.granter.MobileTokenGranter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mirko
 * @Description
 * @createTime 2020年09月20日 00:15:00
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {



    @Autowired
    DataSource dataSource;

    @Value("${jwt.signingKey}")
    private String signingKey;

    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //url:/oauth/token_key,exposes public key for token verification if using JWT tokens
        security.tokenKeyAccess("permitAll()")
                //url:/oauth/check_token allow check token
                .checkTokenAccess("isAuthenticated()")
                // 允许客户端进行表单认证  支持将client参数放在header或body中
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //如何获取授权的客户端信息
//        clients.withClientDetails()
        //从数据库中获取客户端信息，需要创建指定的数据表
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //token
        endpoints.tokenStore(tokenStore())
                //todo 待解析
                .authorizationCodeServices(authorizationCodeServices())
                //存储已经授权对象
                .approvalStore(approvalStore())
                //
                .exceptionTranslator(customExceptionTranslator())
                //token增强
                .tokenEnhancer(tokenEnhancerChain())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                //自定义授权
                .tokenGranter(tokenGranter(endpoints));;

    }


    /***
     * 自定义token认证器
     * @param endpoints
     * @return
     */
    private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
        List<TokenGranter> tokenGranters = Arrays.asList(endpoints.getTokenGranter());
        tokenGranters.add(new MobileTokenGranter(authenticationManager, endpoints.getTokenServices(), endpoints.getClientDetailsService(),endpoints.getOAuth2RequestFactory()));
        return new CompositeTokenGranter(tokenGranters);
    }

    @Bean
    public WebResponseExceptionTranslator<OAuth2Exception> customExceptionTranslator() {
        return new CustomWebResponseExceptionTranslator();
    }


    /**
     * 存储已经授权的对象，可以是用户或者是客户端
     * @return JdbcApprovalStore
     */
    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    /**
     * 授权码持久化
     * @return JdbcAuthorizationCodeServices
     */
    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }


    /**
     * token增强调用链，用于创建token，这里可以存放一些自定义的信息
     * accessTokenConverter 就是用来生成jwt的
     * TokenEnhancerChain是对TokenEnhancer封装，让他支持多个增强器
     * @return TokenEnhancerChain
     */
    @Bean
    public TokenEnhancer tokenEnhancerChain() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(new CustomTokenEnhancer(), accessTokenConverter()));
        return tokenEnhancerChain;
    }


    /**
     * 生成token的增强对象
     * @return JwtTokenStore
     */
    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    /**
     * jwt 转换器，生成jwt信息
     * {@link JwtAccessTokenConverter#enhance(org.springframework.security.oauth2.common.OAuth2AccessToken, org.springframework.security.oauth2.provider.OAuth2Authentication)}
     * @return  JwtAccessTokenConverter
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signingKey);
        return converter;
    }



}
