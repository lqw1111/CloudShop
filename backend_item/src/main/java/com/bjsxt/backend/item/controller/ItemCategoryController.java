package com.bjsxt.backend.item.controller;

import com.bjsxt.backend.item.service.ItemCategoryService;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商品类目
 */
@RestController
@RequestMapping("/backend/itemCategory")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 根据类目id查询子节点
     */
    @PostMapping("/selectItemCategoryByParentId")
    public Result selectItemCategoryByParentId(@RequestParam(value = "id", defaultValue = "0") Long id) {
        try {
            return itemCategoryService.selectItemCategoryByParentId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }
}
