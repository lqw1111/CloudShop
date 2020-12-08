package com.bjsxt.cart.service;

import com.bjsxt.utils.Result;

public interface RedisCartService {
    Result addItem(Long itemId, Integer num, String userId);

    Result showCart(String userId);

    Result updateItemNum(Long itemId, Integer num, String userId);

    Result deleteItemFromCart(Long itemId, String userId);

    Result goSettlement(String[] ids, String userId);
}
