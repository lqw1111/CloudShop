package com.bjsxt.cart.service;

import com.bjsxt.utils.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CookieCartService {
    Result addItem(Long itemId, String userId, Integer num, HttpServletRequest request, HttpServletResponse response);

    Result showCart(HttpServletRequest request, HttpServletResponse response);

    Result updateItemNum(Long itemId, String userId, Integer num, HttpServletRequest request, HttpServletResponse response);

    Result deleteItemFronCart(Long itemId, HttpServletRequest request, HttpServletResponse response);
}
