package com.xum.feignclient.controller;

import com.xum.feignclient.server.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// http://localhost:8772/feign/feignconsumer

@RestController
@RequestMapping(value = "/feign")
public class FeignConsumerController {

    private static final Logger LOG = LoggerFactory.getLogger(FeignConsumerController.class);

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/feignconsumer")
    public String helloConsumer(){
        LOG.info("FeginConsumerController=>helloConsumer");
        return helloService.hello();
    }

    @RequestMapping(value = "/test")
    public String test() {
        return "test";
    }
}
