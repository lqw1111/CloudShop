package com.bjsxt.front.portal.controller;

import com.bjsxt.front.portal.service.ItemService;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询商品
 */

@RestController
@RequestMapping("/frontend/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 查询商品基本信息
     */
    @RequestMapping("/selectItemInfo")
    public Result selectItemInfo(Long itemId) {
        if (itemId == null) {
            itemId = 536563L;
        }
        try {
            return itemService.selectItemInfo(itemId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }

    /**
     * 查询商品的介绍
     */
    @RequestMapping("/selectItemDescByItemId")
    public Result selectItemDescByItemId(Long itemId) {
        if (itemId == null) {
            itemId = 536563L;
        }
        try {
            return itemService.selectItemDescByItemId(itemId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }

    /**
     * 更具商品id查询规格参数
     * @param itemId
     * @return
     */
    @RequestMapping("/selectTbItemParamItemByItemId")
    public Result selectTbItemParamItemByItemId(@RequestParam Long itemId) {
        if (itemId == null) {
            itemId = 536563L;
        }
        try {
            return itemService.selectTbItemParamItemByItemId(itemId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }
}
