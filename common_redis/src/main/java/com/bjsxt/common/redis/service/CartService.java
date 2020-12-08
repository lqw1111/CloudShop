package com.bjsxt.common.redis.service;

import com.bjsxt.utils.CartItem;

import java.util.Map;

public interface CartService {
    void insertCart(Map<String, Object> map);

    Map<String,CartItem> selectCartByUserId(String userId);
}
