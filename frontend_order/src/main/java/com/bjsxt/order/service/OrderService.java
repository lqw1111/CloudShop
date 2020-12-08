package com.bjsxt.order.service;

import com.bjsxt.pojo.TbOrder;
import com.bjsxt.pojo.TbOrderItem;
import com.bjsxt.pojo.TbOrderShipping;
import com.bjsxt.utils.Result;

import java.util.List;

public interface OrderService {

    Result insertOrder(List<TbOrderItem> tbOrderItem, TbOrder tbOrder, TbOrderShipping tbOrderShipping);
}
