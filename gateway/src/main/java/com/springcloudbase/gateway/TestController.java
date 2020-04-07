package com.springcloudbase.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Mirko on 2020/3/12.
 */
@RestController
public class TestController {



    @Autowired
    DiscoveryClient discoveryClient;

    @RequestMapping("/servers")
    public List getClient(){

        List<ServiceInstance> serviceInstance = discoveryClient.getInstances("USER-SERVER-API");
        return serviceInstance;
    }

}
