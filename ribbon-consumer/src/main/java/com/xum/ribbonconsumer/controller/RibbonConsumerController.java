package com.xum.ribbonconsumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

// http://localhost:8771/ribbonconsumer/test

@RestController
@RequestMapping(value = "/ribbonconsumer")
public class RibbonConsumerController {
    private static final Logger LOG = LoggerFactory.getLogger(RibbonConsumerController.class);

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/test")
    @HystrixCommand(fallbackMethod = "testConsumer_ribbon")
    public String testConsumer() {
        LOG.info("RibbonConsumerController=>testConsumer");
        return restTemplate.getForEntity("http://eureka-client/testone/test",String.class).getBody();
    }

    public String testConsumer_ribbon() {
        LOG.info("RibbonConsumerController=>testConsumer_ribbon");
        return "testConsumer_ribbon";
    }

}
