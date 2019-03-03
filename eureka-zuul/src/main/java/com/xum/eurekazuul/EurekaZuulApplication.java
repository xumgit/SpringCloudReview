package com.xum.eurekazuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

// http://localhost:8773/xum/testone/test

@SpringBootApplication
@EnableZuulProxy
@EnableHystrixDashboard
@EnableTurbine
public class EurekaZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaZuulApplication.class, args);
    }

}
