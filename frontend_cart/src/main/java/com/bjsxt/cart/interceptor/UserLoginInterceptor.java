package com.bjsxt.cart.interceptor;

import com.bjsxt.cart.service.UserCheckService;
import com.bjsxt.pojo.TbUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 在结算之前判断用户是否登陆的拦截器
 */
@Component
public class UserLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserCheckService userCheckService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //对用户的token做判断
        String token = request.getParameter("token");
        if (StringUtils.isBlank(token)) {
            return false;
        }
        //如果用户token不为空，则校验用户在redis中是否失效
        TbUser tbUser = this.userCheckService.checkUserToken(token);
        if (tbUser == null) {
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
