package com.bjsxt.common.redis.controller;

import com.bjsxt.common.redis.service.SSOService;
import com.bjsxt.pojo.TbUser;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sso/redis")
public class SSOController {

    @Autowired
    private SSOService ssoService;

    /**
     * 用户缓存到redis中
     */
    @RequestMapping("/insertUser")
    public void insertUser(@RequestBody TbUser tbUser, @RequestParam String userToken) {
        ssoService.insertUser(tbUser, userToken);
    }

    /**
     * 用户退出登陆
     */
    @RequestMapping("/logOut")
    public void logOut(@RequestParam String userToken) {
        ssoService.logOut(userToken);
    }

    /**
     * 根据用户token校验用户在redis中是否失效
     */
    @RequestMapping("/checkUserToken")
    public TbUser checkUserToken(@RequestParam String token) {
        return ssoService.checkUserToken(token);
    }

}
