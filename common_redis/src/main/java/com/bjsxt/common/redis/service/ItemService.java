package com.bjsxt.common.redis.service;

import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;

public interface ItemService {
    void insertItemBasicInfo(TbItem tbItem);

    TbItem selectItemBasicInfo(Long tbItemId);

    void insertItemDesc(TbItemDesc tbItemDesc);

    TbItemDesc selectItemDesc(Long tbItemId);

    void insertItemParamItem(TbItemParamItem tbItemParamItem);

    TbItemParamItem selectItemParamItem(Long tbItemId);
}
