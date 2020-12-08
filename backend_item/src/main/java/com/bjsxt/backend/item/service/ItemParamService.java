package com.bjsxt.backend.item.service;

import com.bjsxt.utils.Result;

public interface ItemParamService {

    Result selectItemParamByItemCatId(Long itemCatId);

    Result selectItemParamAll(Integer page, Integer rows);

    Result insertItemParam(Long itemCatId, String paramData);

    Result deleteItemParamById(Long id);
}
