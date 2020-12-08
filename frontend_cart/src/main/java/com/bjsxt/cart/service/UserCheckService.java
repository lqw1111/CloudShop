package com.bjsxt.cart.service;

import com.bjsxt.pojo.TbUser;

public interface UserCheckService {
    TbUser checkUserToken(String token);
}
