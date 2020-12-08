package com.bjsxt.item.controller;

import com.bjsxt.item.service.ItemParamService;
import com.bjsxt.pojo.TbItemParam;
import com.bjsxt.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/itemParam")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    /**
     * 根据商品分类ID查询规格参数模版
     */
    @PostMapping("/selectItemParamByItemCatId")
    public TbItemParam selectItemParamByItemCatId(@RequestParam("itemCatId") Long itemCatId) {
        return this.itemParamService.selectItemParamByItemCatId(itemCatId);
    }

    /**
     * 查询所有规格参数模版
     */
    @RequestMapping("/selectItemParamAll")
    public PageResult selectItemParamAll(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows) {
        return this.itemParamService.selectItemParamAll(page, rows);
    }

    /**
     * 根据商品分类添加商品参数模版
     */
    @RequestMapping("/insertItemParam")
    public Integer insertItemParam(@RequestBody TbItemParam tbItemParam) {
        return itemParamService.insertItemParam(tbItemParam);
    }

    /**
     * 根据规格参数模版的Id删除
     */
    @RequestMapping("/deleteItemParamById")
    public Integer deleteItemParamById(@RequestParam Long id) {
        return itemParamService.deleteItemParamById(id);
    }
}
