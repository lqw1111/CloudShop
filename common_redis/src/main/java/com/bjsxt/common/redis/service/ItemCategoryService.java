package com.bjsxt.common.redis.service;

import com.bjsxt.utils.CatResult;

public interface ItemCategoryService {

    void insertItemCategory(CatResult catResult);

    CatResult selectItemCategory();
}
