package com.xum.zipkinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
//import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import zipkin2.storage.mysql.v1.MySQLStorage;
import zipkin2.server.internal.EnableZipkinServer;

import javax.sql.DataSource;

/*
* https://github.com/cc6688211/springCloud
* */

@SpringBootApplication
@EnableEurekaClient
@EnableZipkinServer
//@EnableZipkinStreamServer
public class ZipkinServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication.class, args);
    }

    @Bean
    @Primary
    public MySQLStorage mySQLStorage(DataSource datasource) {
        return MySQLStorage.newBuilder().datasource(datasource).executor(Runnable::run).build();
    }
}
