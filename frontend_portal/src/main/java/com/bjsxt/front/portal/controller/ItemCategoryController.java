package com.bjsxt.front.portal.controller;

import com.bjsxt.front.portal.service.ItemCategoryService;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/frontend/itemCategory")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 查询首页商品分类
     */
    @RequestMapping(value = "/selectItemCategoryAll")
    public Result selectItemCategoryAll() {
        try {
            return itemCategoryService.selectItemCategoryAll();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return Result.build(500, "error");
    }
}
