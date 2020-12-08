package com.bjsxt.common.redis.service.Impl;

import com.bjsxt.common.redis.service.CartService;
import com.bjsxt.utils.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    @Value("${frontend_cart_redis_key}")
    private String frontend_cart_redis_key;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void insertCart(Map<String, Object> map) {
        String userId = (String) map.get("userId");
        Map<String, CartItem> cart = (Map<String, CartItem>) map.get("cart");
        this.redisTemplate.opsForHash().put(frontend_cart_redis_key, userId, cart);
    }

    @Override
    public Map<String, CartItem> selectCartByUserId(String userId) {
        return (Map<String, CartItem>) this.redisTemplate.opsForHash().get(frontend_cart_redis_key, userId);
    }
}
