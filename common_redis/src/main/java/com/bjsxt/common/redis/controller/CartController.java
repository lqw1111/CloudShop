package com.bjsxt.common.redis.controller;

import com.bjsxt.common.redis.service.CartService;
import com.bjsxt.utils.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/redis/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 将购物车缓存到redis中
     */
    @RequestMapping("/insertCart")
    public void insertCart(@RequestBody Map<String, Object> map) {
        cartService.insertCart(map);
    }

    /**
     * 根据用户id查询用户购物车
     */
    @RequestMapping("/selectCartByUserId")
    public Map<String, CartItem> selectCartByUserId(@RequestParam String userId) {
        return cartService.selectCartByUserId(userId);
    }
}
