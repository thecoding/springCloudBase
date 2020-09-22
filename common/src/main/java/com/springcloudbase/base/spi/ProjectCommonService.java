package com.springcloudbase.base.spi;

/**
 * Created by Mirko on 2020/4/7.
 */
public class ProjectCommonService implements CommonService{

    @Override
    public String getPath() {
        System.out.println("project");
        return "project";
    }
}


