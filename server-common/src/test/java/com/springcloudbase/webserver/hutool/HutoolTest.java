package com.springcloudbase.webserver.hutool;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.thread.ThreadUtil;
import com.springcloudbase.webserver.servervo.BaseUser;
import org.junit.Test;

/**
 * Created by Mirko on 2020/4/12.
 */
public class HutoolTest {

    @Test
    public void testCache(){
        TimedCache<String, BaseUser> timedCache = CacheUtil.newTimedCache(5*1000);
        timedCache.put("11",new BaseUser(1111,"name"));
        ThreadUtil.sleep(6 * 1000);
        BaseUser baseUser =  timedCache.get("11");
        System.out.println(baseUser == null  ? null : baseUser.toString());
    }
}
