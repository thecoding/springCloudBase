package com.springcloudbase.order.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (AuthClient)实体类
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
public class AuthClient implements Serializable {
    private static final long serialVersionUID = 397915875061738787L;
    
    private Integer id;
    /**
    * 服务编码
    */
    private String code;
    /**
    * 服务密钥
    */
    private String secret;
    /**
    * 服务名
    */
    private String name;
    /**
    * 是否锁定
    */
    private String locked;
    /**
    * 描述
    */
    private String description;
    /**
    * 创建时间
    */
    private Date crtTime;
    /**
    * 创建人
    */
    private String crtUser;
    /**
    * 创建人姓名
    */
    private String crtName;
    /**
    * 创建主机
    */
    private String crtHost;
    /**
    * 更新时间
    */
    private Date updTime;
    /**
    * 更新人
    */
    private String updUser;
    /**
    * 更新姓名
    */
    private String updName;
    /**
    * 更新主机
    */
    private String updHost;
    
    private String attr1;
    
    private String attr2;
    
    private String attr3;
    
    private String attr4;
    
    private String attr5;
    
    private String attr6;
    
    private String attr7;
    
    private String attr8;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocked() {
        return locked;
    }

    public void setLocked(String locked) {
        this.locked = locked;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public String getCrtUser() {
        return crtUser;
    }

    public void setCrtUser(String crtUser) {
        this.crtUser = crtUser;
    }

    public String getCrtName() {
        return crtName;
    }

    public void setCrtName(String crtName) {
        this.crtName = crtName;
    }

    public String getCrtHost() {
        return crtHost;
    }

    public void setCrtHost(String crtHost) {
        this.crtHost = crtHost;
    }

    public Date getUpdTime() {
        return updTime;
    }

    public void setUpdTime(Date updTime) {
        this.updTime = updTime;
    }

    public String getUpdUser() {
        return updUser;
    }

    public void setUpdUser(String updUser) {
        this.updUser = updUser;
    }

    public String getUpdName() {
        return updName;
    }

    public void setUpdName(String updName) {
        this.updName = updName;
    }

    public String getUpdHost() {
        return updHost;
    }

    public void setUpdHost(String updHost) {
        this.updHost = updHost;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    public String getAttr4() {
        return attr4;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }

    public String getAttr5() {
        return attr5;
    }

    public void setAttr5(String attr5) {
        this.attr5 = attr5;
    }

    public String getAttr6() {
        return attr6;
    }

    public void setAttr6(String attr6) {
        this.attr6 = attr6;
    }

    public String getAttr7() {
        return attr7;
    }

    public void setAttr7(String attr7) {
        this.attr7 = attr7;
    }

    public String getAttr8() {
        return attr8;
    }

    public void setAttr8(String attr8) {
        this.attr8 = attr8;
    }

}