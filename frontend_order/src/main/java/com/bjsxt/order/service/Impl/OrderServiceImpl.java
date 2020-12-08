package com.bjsxt.order.service.Impl;

import com.bjsxt.mapper.TbOrderItemMapper;
import com.bjsxt.mapper.TbOrderMapper;
import com.bjsxt.mapper.TbOrderShippingMapper;
import com.bjsxt.order.feign.CommonRedisFeignClient;
import com.bjsxt.order.feign.FrontendCartFeignClient;
import com.bjsxt.order.service.OrderService;
import com.bjsxt.pojo.TbOrder;
import com.bjsxt.pojo.TbOrderItem;
import com.bjsxt.pojo.TbOrderShipping;
import com.bjsxt.utils.IDUtils;
import com.bjsxt.utils.Result;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;

    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;

    @Autowired
    private FrontendCartFeignClient frontendCartFeignClient;

    @Override
    @LcnTransaction
    public Result insertOrder(List<TbOrderItem> tbOrderItem, TbOrder tbOrder, TbOrderShipping tbOrderShipping) {
        //获取订单
        Long orderId = this.commonRedisFeignClient.selectOrderId();

        //补齐TbOrder数据
        tbOrder.setOrderId(orderId.toString());
        tbOrder.setStatus(1);

        Date date = new Date();
        tbOrder.setCreateTime(date);
        tbOrder.setUpdateTime(date);

        //0:未评价 1：已评价
        tbOrder.setBuyerRate(0);

        this.tbOrderMapper.insert(tbOrder);

        //插入订单中所包含的商品
        for (TbOrderItem item : tbOrderItem) {
            item.setId(IDUtils.genItemId() + "");
            item.setOrderId(orderId.toString());
            tbOrderItemMapper.insert(item);
            //将订单中的商品从购物车删除
            frontendCartFeignClient.deleteItemFronCart(Long.parseLong(item.getId()), tbOrder.getUserId().toString());
        }

        //插入物流表数据
        tbOrderShipping.setOrderId(orderId.toString());
        tbOrderShipping.setUpdated(date);
        tbOrderShipping.setCreated(date);

        this.tbOrderShippingMapper.insert(tbOrderShipping);

        return Result.ok(orderId);
    }
}
