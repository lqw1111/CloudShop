package com.bjsxt.item.service;

import com.bjsxt.pojo.TbItemParam;
import com.bjsxt.utils.PageResult;

public interface ItemParamService {

    TbItemParam selectItemParamByItemCatId(Long itemCatId);

    PageResult selectItemParamAll(Integer page, Integer rows);

    Integer insertItemParam(TbItemParam tbItemParam);

    Integer deleteItemParamById(Long id);
}
