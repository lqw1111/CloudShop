package com.bjsxt.cart.controller;

import com.bjsxt.cart.service.CookieCartService;
import com.bjsxt.cart.service.RedisCartService;
import com.bjsxt.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CookieCartService cookieCartService;

    @Autowired
    private RedisCartService redisCartService;

    /**
     * 将商品假如购物车
     */
    @RequestMapping("/addItem")
    public Result addItem(Long itemId, String userId, @RequestParam(defaultValue = "1") Integer num, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (StringUtils.isBlank(userId)) {
                //用户未登陆状态下
                return cookieCartService.addItem(itemId, userId, num, request, response);
            } else {
                //用户以登陆状态下
                return this.redisCartService.addItem(itemId, num, userId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }

    /**
     * 查看购物车
     */
    @RequestMapping("/showCart")
    public Result showCart(String userId, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (StringUtils.isBlank(userId)) {
                //用户未登陆状态下
                return cookieCartService.showCart(request, response);
            } else {
                //用户以登陆状态下
                return redisCartService.showCart(userId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }

    /**
     * 修改购物车中商品数量的变化
     */
    @RequestMapping("/updateItemNum")
    public Result updateItemNum(Long itemId, String userId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (StringUtils.isBlank(userId)) {
                //用户未登陆状态下
                return cookieCartService.updateItemNum(itemId, userId, num, request, response);
            } else {
                //用户以登陆状态下
                return redisCartService.updateItemNum(itemId, num, userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }

    /**
     * 删除购物车中的商品
     */
    @RequestMapping("/deleteItemFromCart")
    public Result deleteItemFronCart(@RequestParam Long itemId,@RequestParam String userId, HttpServletRequest request, HttpServletResponse response) {
        try {
            if (StringUtils.isBlank(userId)) {
                //用户未登陆状态下
                return cookieCartService.deleteItemFronCart(itemId, request, response);
            } else {
                //用户以登陆状态下
                return redisCartService.deleteItemFromCart(itemId, userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }

    /**
     * 去结算
     */
    @RequestMapping("/goSettlement")
    public Result goSettlement(String[] ids, String userId) {
        try {
            return redisCartService.goSettlement(ids, userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok();
    }

}
