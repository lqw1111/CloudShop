package com.bjsxt.item;

import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableDistributedTransaction
@MapperScan("com.bjsxt.mapper")
public class CommonItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommonItemApplication.class, args);
    }
}
