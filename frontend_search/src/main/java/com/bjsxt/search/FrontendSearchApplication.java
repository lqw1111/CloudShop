package com.bjsxt.search;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.bjsxt.mapper")
public class FrontendSearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontendSearchApplication.class, args);
    }
}
