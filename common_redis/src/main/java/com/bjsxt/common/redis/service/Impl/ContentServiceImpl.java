package com.bjsxt.common.redis.service.Impl;

import com.bjsxt.common.redis.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${frontend_ad_redis_key}")
    private String frontend_ad_redis_key;

    @Override
    public void insertContentAD(List<Map> list) {
        this.redisTemplate.opsForValue().set(frontend_ad_redis_key, list);
    }

    @Override
    public List<Map> selectContentAD() {
        return (List<Map>) this.redisTemplate.opsForValue().get(frontend_ad_redis_key);
    }
}
