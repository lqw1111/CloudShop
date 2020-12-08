package com.bjsxt.common.redis.controller;

import com.bjsxt.common.redis.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParam;
import com.bjsxt.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 缓存商品基本信息
     */
    @RequestMapping("/insertItemBasicInfo")
    public void insertItemBasicInfo(@RequestBody TbItem tbItem){
        itemService.insertItemBasicInfo(tbItem);
    }

    /**
     * 查询缓存中的商品基本信息
     */
    @RequestMapping("/selectItemBasicInfo")
    public TbItem selectItemBasicInfo(@RequestParam Long tbItemId) {
        return itemService.selectItemBasicInfo(tbItemId);
    }

    /**
     * 缓存商品介绍信息
     */
    @RequestMapping("/insertItemDesc")
    public void insertItemDesc(@RequestBody TbItemDesc tbItemDesc) {
        itemService.insertItemDesc(tbItemDesc);
    }

    /**
     * 查询缓存中商品介绍
     */
    @RequestMapping("/selectItemDesc")
    public TbItemDesc selectItemDesc(@RequestParam Long tbItemId) {
        return itemService.selectItemDesc(tbItemId);
    }

    /**
     * 缓存商品的规格参数
     */
    @RequestMapping("/insertItemParamItem")
    public void insertItemParamItem(@RequestBody TbItemParamItem tbItemParamItem) {
        itemService.insertItemParamItem(tbItemParamItem);
    }

    /**
     * 查询缓存的商品规格参数
     */
    @RequestMapping("/selectItemParamItem")
    public TbItemParamItem selectItemParamItem(@RequestParam Long tbItemId) {
        return itemService.selectItemParamItem(tbItemId);
    }

}
