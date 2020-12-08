package com.bjsxt.backend.item.service;


import com.bjsxt.pojo.TbItem;
import com.bjsxt.utils.Result;

public interface ItemService {

    Result selectTbItemAllByPage(Integer page, Integer rows);
    Result insertTbItem(TbItem tbItem, String desc, String itemParams) throws RuntimeException;
    Result deleteItemById(Long itemId);
    Result preUpdateItem(Long itemId);
    Result updateTbItem(TbItem tbItem, String desc, String itemParams);
}
