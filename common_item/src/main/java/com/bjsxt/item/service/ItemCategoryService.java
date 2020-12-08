package com.bjsxt.item.service;

import com.bjsxt.pojo.TbItemCat;
import com.bjsxt.utils.CatResult;

import java.util.List;

public interface ItemCategoryService {
    List<TbItemCat> selectItemCategoryByParentId(Long id);

    CatResult selectItemCategoryAll();
}
