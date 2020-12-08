package com.bjsxt.common.redis.service.Impl;

import com.bjsxt.common.redis.service.ItemCategoryService;
import com.bjsxt.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Value("${frontend_catresult_redis_key}")
    private String frontend_catresult_redis_key;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 缓存中添加首页商品分类
     * @param catResult
     */
    @Override
    public void insertItemCategory(CatResult catResult) {
        this.redisTemplate.opsForValue().set(frontend_catresult_redis_key, catResult);
    }

    @Override
    public CatResult selectItemCategory() {
        return (CatResult) this.redisTemplate.opsForValue().get(this.frontend_catresult_redis_key);
    }
}
