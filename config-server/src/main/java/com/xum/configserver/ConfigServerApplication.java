package com.xum.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.config.server.EnableConfigServer;

// https://www.cnblogs.com/ityouknow/p/6931958.html
// https://www.cnblogs.com/wade-luffy/p/6003668.html

/*
* change spring cloud config, push to git,
* than open cmd, input: curl -X POST http://localhost:8765/actuator/bus-refresh
* refresh microservice
* */

@EnableEurekaClient
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
