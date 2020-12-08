package com.bjsxt.item.service;

import com.bjsxt.pojo.TbItemParamItem;

public interface ItemParamItemService {
    Integer insertTbItemParamItem(TbItemParamItem tbItemParamItem);

    Integer updateItemParamItem(TbItemParamItem tbItemParamItem);

    TbItemParamItem selectTbItemParamItemByItemId(Long itemId);
}
