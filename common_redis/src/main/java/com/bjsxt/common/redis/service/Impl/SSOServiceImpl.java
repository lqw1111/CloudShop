package com.bjsxt.common.redis.service.Impl;

import com.bjsxt.common.redis.service.SSOService;
import com.bjsxt.pojo.TbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class SSOServiceImpl implements SSOService {

    @Value("${user_session_redis_key}")
    private String user_session_redis_key;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void insertUser(TbUser tbUser, String userToken) {
        //将密码设置成空
        tbUser.setPassword("");
        this.redisTemplate.opsForValue().set(this.user_session_redis_key + ":" + userToken, tbUser, 1, TimeUnit.DAYS);
    }

    @Override
    public void logOut(String userToken) {
        this.redisTemplate.delete(this.user_session_redis_key + ":" + userToken);
    }

    @Override
    public TbUser checkUserToken(String token) {
        return (TbUser) this.redisTemplate.opsForValue().get(this.user_session_redis_key + ":" + token);
    }

}
