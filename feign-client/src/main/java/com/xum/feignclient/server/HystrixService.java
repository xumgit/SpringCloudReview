package com.xum.feignclient.server;

import org.springframework.stereotype.Service;

@Service
public class HystrixService implements HelloService {

    @Override
    public String hello() {
        return " HelloService=>HystrixService=>eureka-client is unable ...";
    }

}
