package com.bjsxt.item.controller;

import com.bjsxt.item.service.ItemParamItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/itemParamItem")
public class ItemParamItemController {

    @Autowired
    private ItemParamItemService itemParamItemService;

    /**
     * 添加商品规格参数
     */
    @RequestMapping("/insertTbItemParamItem")
    public Integer insertTbItemParamItem(@RequestBody TbItemParamItem tbItemParamItem) {
        return itemParamItemService.insertTbItemParamItem(tbItemParamItem);
    }

    /**
     * 更新商品规格参数
     */
    @RequestMapping("/updateItemParamItem")
    public Integer updateItemParamItem(@RequestBody TbItemParamItem tbItemParamItem){
        return itemParamItemService.updateItemParamItem(tbItemParamItem);
    }

    /**
     * 根据商品ID查询商品规格参数
     */
    @RequestMapping("/selectTbItemParamItemByItemId")
    public TbItemParamItem selectTbItemParamItemByItemId(@RequestParam Long itemId) {
        return itemParamItemService.selectTbItemParamItemByItemId(itemId);
    }

}
