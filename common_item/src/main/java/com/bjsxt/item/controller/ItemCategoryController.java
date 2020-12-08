package com.bjsxt.item.controller;

import com.bjsxt.item.service.ItemCategoryService;
import com.bjsxt.pojo.TbItemCat;
import com.bjsxt.utils.CatNode;
import com.bjsxt.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 商品类目
 */
@RestController
@RequestMapping("/service/itemCategory")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    /**
     * 根据父节点查询子节点
     */
    @PostMapping("/selectItemCategoryByParentId")
    public List<TbItemCat> selectItemCategoryByParentId(@RequestParam("id") Long id) {
        return this.itemCategoryService.selectItemCategoryByParentId(id);
    }

    /**
     * 查询首页商品分类
     */
    @RequestMapping("/selectItemCategoryAll")
    public CatResult selectItemCategoryAll() {
        return this.itemCategoryService.selectItemCategoryAll();
    }

}
