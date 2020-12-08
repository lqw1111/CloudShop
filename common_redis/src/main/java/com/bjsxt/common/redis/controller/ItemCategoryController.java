package com.bjsxt.common.redis.controller;

import com.bjsxt.common.redis.service.ItemCategoryService;
import com.bjsxt.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis/itemCategory")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 向redis中添加缓存数据
     */
    @RequestMapping("/insertItemCategory")
    public void insertItemCategory(@RequestBody CatResult catResult) {
        itemCategoryService.insertItemCategory(catResult);
    }

    /**
     * 查询Redis中缓存的首页商品分类
     */
    @RequestMapping("/selectItemCategory")
    public CatResult selectItemCategory() {
        return itemCategoryService.selectItemCategory();
    }
}
