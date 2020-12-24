package com.springcloudbase.order.dao;

import com.springcloudbase.order.entity.AuthClient;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * (AuthClient)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
public interface AuthClientDaoEnhance extends AuthClientDao{

    List<AuthClient> getPageAuthClient();

    List<Map<String,String>> getPageAuthClient1(@Param("secret")String secret,@Param("id")String id);

}