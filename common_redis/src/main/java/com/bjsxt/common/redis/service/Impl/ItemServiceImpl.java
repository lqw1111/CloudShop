package com.bjsxt.common.redis.service.Impl;

import com.bjsxt.common.redis.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${frontend_item_basic_info_key}")
    private String frontend_item_basic_info_key;

    @Value("${frontend_item_desc_key}")
    private String frontend_item_desc_key;

    @Value("${frontend_item_param_key}")
    private String frontend_item_param_key;

    /**
     * 缓存基本信息
     * @param tbItem
     */
    @Override
    public void insertItemBasicInfo(TbItem tbItem) {
        this.redisTemplate.opsForValue().set(this.frontend_item_basic_info_key + ":" + tbItem.getId() ,tbItem);
    }

    @Override
    public TbItem selectItemBasicInfo(Long tbItemId) {
        return (TbItem) this.redisTemplate.opsForValue().get(frontend_item_basic_info_key + ":" + tbItemId);
    }

    @Override
    public void insertItemDesc(TbItemDesc tbItemDesc) {
        this.redisTemplate.opsForValue().set(this.frontend_item_desc_key + ":" + tbItemDesc.getItemId(),tbItemDesc);
    }

    @Override
    public TbItemDesc selectItemDesc(Long tbItemId) {
        return (TbItemDesc) this.redisTemplate.opsForValue().get(this.frontend_item_desc_key + ":" + tbItemId);
    }

    @Override
    public void insertItemParamItem(TbItemParamItem tbItemParamItem) {
        this.redisTemplate.opsForValue().set(frontend_item_param_key + ":" + tbItemParamItem.getItemId(),tbItemParamItem);
    }

    @Override
    public TbItemParamItem selectItemParamItem(Long tbItemId) {
        return (TbItemParamItem) this.redisTemplate.opsForValue().get(frontend_item_param_key + ":" + tbItemId);
    }
}
