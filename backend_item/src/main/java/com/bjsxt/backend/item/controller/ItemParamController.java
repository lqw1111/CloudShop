package com.bjsxt.backend.item.controller;

import com.bjsxt.backend.item.service.ItemParamService;
import com.bjsxt.utils.Result;
import io.protostuff.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/backend/itemParam")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    /**
     * 根据商品分类id查询规格参数模版
     */
    @GetMapping("/selectItemParamByItemCatId/{itemCatId}")
    public Result selectItemParamByItemCatId(@PathVariable("itemCatId") Long itemCatId) {
        try {
            return itemParamService.selectItemParamByItemCatId(itemCatId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }

    /**
     * 查询商品规格参数
     */
    @RequestMapping("/selectItemParamAll")
    public Result selectItemParamAll(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "rows", defaultValue = "30")Integer rows) {
        try {
            return itemParamService.selectItemParamAll(page, rows);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }

    /**
     * 添加商品分类规格参数模版
     */
    @RequestMapping("/insertItemParam")
    public Result insertItemParam(Long itemCatId, String paramData) {
        try {
            return itemParamService.insertItemParam(itemCatId, paramData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }

    /**
     * 删除规格参数模版
     */
    @RequestMapping("/deleteItemParamById")
    public Result deleteItemParamById(Long id) {
        try {
            return itemParamService.deleteItemParamById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }
}
