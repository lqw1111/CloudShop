package com.bjsxt.cart.feign;

import com.bjsxt.pojo.TbUser;
import com.bjsxt.utils.CartItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "common-redis")
public interface CommonRedisFeignClient {

    @PostMapping("/redis/cart/selectCartByUserId")
    Map<String, CartItem> selectCartByUserId(@RequestParam("userId") String userId);

    @PostMapping("/redis/cart/insertCart")
    void insertCart(@RequestBody Map<String, Object> map);

    @PostMapping("/sso/redis/checkUserToken")
    TbUser checkUserToken(@RequestParam("token") String token);

}
