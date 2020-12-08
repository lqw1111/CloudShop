package com.bjsxt.common.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CommonRedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonRedisApplication.class, args);
    }
}
