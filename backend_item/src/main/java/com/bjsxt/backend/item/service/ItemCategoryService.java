package com.bjsxt.backend.item.service;

import com.bjsxt.utils.Result;

public interface ItemCategoryService {

    Result selectItemCategoryByParentId(Long id);
}
