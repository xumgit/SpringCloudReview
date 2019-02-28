package com.xum.eurekaclient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.List;

@RefreshScope
@RestController
@RequestMapping(value = "/testone")
public class TestOneController {

    private static final Logger LOG = LoggerFactory.getLogger(TestOneController.class);

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    String port;

    /* from git config */
    @Value("${democonfigclient.message}")
    String message;

    /* from git config */
    @Value("${foo}")
    String foo;

    @RequestMapping(value = "/test")
    public String test(@RequestParam(value = "name", required = false, defaultValue = "testOneClient")String name) {
        List<ServiceInstance> list = discoveryClient.getInstances("eureka-client");
        LOG.info("discoveryClient.getServices().size() = " + discoveryClient.getServices().size());
        for( String s :  discoveryClient.getServices()){
            List<ServiceInstance> serviceInstances =  discoveryClient.getInstances(s);
            for(ServiceInstance si : serviceInstances){
                LOG.info("services:" + s + ":getHost()=" + si.getHost());
                LOG.info("services:" + s + ":getPort()=" + si.getPort());
                LOG.info("services:" + s + ":getServiceId()=" + si.getServiceId());
                LOG.info("services:" + s + ":getUri()=" + si.getUri());
            }
        }
        String info = "Hi " + name + ", this is EurekaClient, port is " + port;
        return info;
    }

    @RequestMapping(value = "/config", method = RequestMethod.GET)
    public String config() {
        String info = "port:" + port + ",message:" + message + ",foo:" + foo;
        return info;
    }
}
