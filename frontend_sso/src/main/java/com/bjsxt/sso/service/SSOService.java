package com.bjsxt.sso.service;

import com.bjsxt.pojo.TbUser;
import com.bjsxt.utils.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SSOService {
    Result checkUserInfo(String checkValue, Integer checkFlag);

    Result userRegister(TbUser user);

    Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);

    Result logOut(String token);
}
