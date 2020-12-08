package com.bjsxt.item.service;

import com.bjsxt.pojo.TbItem;
import com.bjsxt.utils.PageResult;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public interface ItemService {

    PageResult selectTbItemAllByPage(Integer page, Integer rows);
    Integer insertTBItem(TbItem tbItem);
    Integer updateItemById(TbItem tbItem);
    Map<String, Object> preUpdateItem(Long itemId);

    TbItem selectItemInfo(Long itemId);
}
