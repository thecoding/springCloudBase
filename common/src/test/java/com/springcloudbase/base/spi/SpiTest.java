package com.springcloudbase.base.spi;

import org.junit.Test;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by Mirko on 2020/4/7.
 */
public class SpiTest {


    /**
     * spi 目录 META-INF/services 一定要一级一级去建目录。。。
     */
    @Test
    public void test1(){
        ServiceLoader<CommonService> serviceLoader = ServiceLoader.load(CommonService.class);
        Iterator<CommonService> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            CommonService service = iterator.next();
            service.getPath();
        }
    }
}
