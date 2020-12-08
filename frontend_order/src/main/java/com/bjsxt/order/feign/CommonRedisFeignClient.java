package com.bjsxt.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "common-redis")
public interface CommonRedisFeignClient {

    @PostMapping("/redis/order/selectOrderId")
    Long selectOrderId();
}
