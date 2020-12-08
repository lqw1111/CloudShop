package com.bjsxt.cart.service.Impl;

import com.bjsxt.cart.feign.CommonRedisFeignClient;
import com.bjsxt.cart.service.UserCheckService;
import com.bjsxt.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCheckServiceImpl implements UserCheckService {

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    @Override
    public TbUser checkUserToken(String token) {
        return commonRedisFeignClient.checkUserToken(token);
    }
}
