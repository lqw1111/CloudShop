package com.bjsxt.sso.service.Impl;

import com.bjsxt.mapper.TbUserMapper;
import com.bjsxt.pojo.TbUser;
import com.bjsxt.pojo.TbUserExample;
import com.bjsxt.sso.feign.CommonRedisFeignClient;
import com.bjsxt.sso.service.SSOService;
import com.bjsxt.utils.*;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class SSOServiceImpl implements SSOService {

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    @Autowired
    private TbUserMapper tbUserMapper;

    @Value("${cart_cookie_name}")
    private String cart_cookie_name;

    @Override
    public Result checkUserInfo(String checkValue, Integer checkFlag) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        if (checkFlag == 1) {
            criteria.andUsernameEqualTo(checkValue);
        } else if (checkFlag == 2) {
            criteria.andPhoneEqualTo(checkValue);
        }
        Integer row = this.tbUserMapper.countByExample(example);
        if (row > 0) {
            return Result.error("数据不可用");
        }
        return Result.ok();
    }

    @Override
    @LcnTransaction
    public Result userRegister(TbUser user) {
        //密码做加密处理
        String pwd = MD5Utils.digest(user.getPassword());
        user.setPassword(pwd);
        user.setUpdated(new Date());
        user.setCreated(new Date());
        this.tbUserMapper.insert(user);
        return Result.ok();
    }

    @Override
    public Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        //根据用户名和密码查询数据库
        TbUser tbUser = this.login(username, password);
        if (tbUser == null) {
            return Result.error("用户名或密码有误，请重新输入");
        }

        //将用户添加到Redis中
        String userToken = UUID.randomUUID().toString();
        Integer flag = this.insertUserToRedis(tbUser, userToken);
        if (flag == 500) {
            return Result.error("登陆失败");
        }
        Map<String, String> map = new HashMap<>();
        map.put("token", userToken);
        map.put("userid", tbUser.getId() + "");
        map.put("username", tbUser.getUsername());

        //同步临时购物车到永久购物车
        this.syncCart(tbUser.getId().toString(), request);

        //同步购物车成功后，需要将临时购物车中的商品删除
        this.deleteCookieCart(request, response);
        return Result.ok(map);
    }

    private void syncCart(String userId, HttpServletRequest request) {
        //获取临时购物车
        Map<String, CartItem> cookieCart = this.getCart(request);

        //获取永久购物车
        Map<String ,CartItem> redisCart = this.getCart(userId);

        //删除永久购物车中所包含临时购物车中的商品
        Set<String> keys = cookieCart.keySet();
        for (String key : keys) {
            redisCart.remove(key);
        }

        //永久购物车写回到redis中
        redisCart.putAll(cookieCart);

        this.addCartToRedis(userId, redisCart);
    }

    private void deleteCookieCart(HttpServletRequest request, HttpServletResponse response) {
        CookieUtils.deleteCookie(request, response, this.cart_cookie_name);
    }

    private void addCartToRedis(String userId, Map<String, CartItem> cart) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("cart", cart);
        this.commonRedisFeignClient.insertCart(map);
    }

    private Map<String, CartItem> getCart(HttpServletRequest request) {
        //存在临时购物车
        String cartJson = CookieUtils.getCookieValue(request, this.cart_cookie_name, true);
        if (StringUtils.isBlank(cartJson)) {
            //临时购物车不存在
            return new HashMap<String, CartItem>();
        }
        //如果含有临时购物车，那么需要json转换
        try {
            Map<String, CartItem> map = JsonUtils.jsonToMap(cartJson, CartItem.class);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<String, CartItem>();
    }

    private Map<String, CartItem> getCart(String userId) {
        try {
            Map<String, CartItem> cart = this.commonRedisFeignClient.selectCartByUserId(userId);
            if (cart == null) {
                cart = new HashMap<String, CartItem>();
            }
            return cart;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashMap<String, CartItem>();
    }

    @Override
    public Result logOut(String token) {
        this.commonRedisFeignClient.logOut(token);
        return Result.ok();
    }

    /**
     * 用户登陆业务处理
     * @param username
     * @param password
     * @return
     */
    private TbUser login(String username, String password) {
        String pwd = MD5Utils.digest(password);
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(pwd);
        List<TbUser> list = this.tbUserMapper.selectByExample(example);
        if (list == null || list.size() <= 0) {
            return null;
        }
        return list.get(0);
    }

    private Integer insertUserToRedis(TbUser tbUser, String userToken) {
        try {
            commonRedisFeignClient.insertUser(tbUser, userToken);
            return 200;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 500;
    }
}
