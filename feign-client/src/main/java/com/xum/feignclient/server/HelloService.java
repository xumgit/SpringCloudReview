package com.xum.feignclient.server;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "eureka-client")
public interface HelloService {

    @RequestMapping(value = "/testone/test")
    String hello();

}
