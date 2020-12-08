package com.bjsxt.backend.item.controller;

import com.bjsxt.backend.item.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/backend/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 查询商品并分页
     * @return
     */
    @RequestMapping(value = "/selectTbItemAllByPage", method = RequestMethod.GET)
    public Result selectTbItemAllByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "2") Integer rows) {
        try {
            return this.itemService.selectTbItemAllByPage(page, rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }

    /**
     * 添加商品
     */
    @GetMapping("/insertTbItem")
    public Result insertTbItem(TbItem tbItem, String desc, String itemParams){
        try {
            return this.itemService.insertTbItem(tbItem, desc, itemParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }

    /**
     * 删除商品
     */
    @RequestMapping("/deleteItemById")
    public Result deleteItemById(Long itemId) {
        try {
            return this.itemService.deleteItemById(itemId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }

    /**
     * 育更新商品查询
     */
    @RequestMapping("/preUpdateItem")
    public Result preUpdateItem(Long itemId) {
        try {
            return this.itemService.preUpdateItem(itemId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }

    /**
     * 商品更新
     */
    @RequestMapping(value = "/updateTbItem", method = RequestMethod.GET)
    public Result updateTbItem(TbItem tbItem, String desc, String itemParams) {
        try {
            return itemService.updateTbItem(tbItem, desc, itemParams);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }

}
