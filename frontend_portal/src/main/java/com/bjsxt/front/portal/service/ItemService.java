package com.bjsxt.front.portal.service;

import com.bjsxt.utils.Result;

public interface ItemService {
    Result selectItemInfo(Long itemId);

    Result selectItemDescByItemId(Long itemId);

    Result selectTbItemParamItemByItemId(Long itemId);
}
