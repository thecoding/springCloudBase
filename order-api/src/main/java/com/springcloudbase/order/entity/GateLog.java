package com.springcloudbase.order.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (GateLog)实体类
 *
 * @author makejava
 * @since 2020-12-23 00:18:03
 */
public class GateLog implements Serializable {
    private static final long serialVersionUID = -72600925586733465L;
    /**
    * 序号
    */
    private Integer id;
    /**
    * 菜单
    */
    private String menu;
    /**
    * 操作
    */
    private String opt;
    /**
    * 资源路径
    */
    private String uri;
    /**
    * 操作时间
    */
    private Date crtTime;
    /**
    * 操作人ID
    */
    private String crtUser;
    /**
    * 操作人
    */
    private String crtName;
    /**
    * 操作主机
    */
    private String crtHost;
    
    private String body;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

}