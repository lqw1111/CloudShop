package com.bjsxt.common.redis.service;

import com.bjsxt.pojo.TbUser;
import com.bjsxt.utils.Result;

public interface SSOService {

    void insertUser(TbUser tbUser, String userToken);

    void logOut(String userToken);

    TbUser checkUserToken(String token);
}
