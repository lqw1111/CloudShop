package com.bjsxt.common.redis.service.Impl;

import com.bjsxt.common.redis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Value("${order_item_id_key}")
    private String order_item_id_key;

    @Value("${init_item_id}")
    private Long init_item_id;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Long selectOrderId() {
        //查询redis中的订单id
        Integer value = (Integer) this.redisTemplate.opsForValue().get(this.order_item_id_key);
        //如果订单id的值不存在，将默认值set到redis中
        if (value == null || value <= 0) {
            this.redisTemplate.opsForValue().set(this.order_item_id_key, this.init_item_id);
        }
        //将通过redis中的自增张将默认值做递增处理
        Long orderId = this.redisTemplate.opsForValue().increment(this.order_item_id_key);
        return orderId;
    }
}
