package com.bjsxt.backend.item.feign;

import com.bjsxt.backend.item.fallback.CommonItemFeignClientFallbackFactory;
import com.bjsxt.pojo.*;
import com.bjsxt.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(value = "common-item", fallbackFactory = CommonItemFeignClientFallbackFactory.class)
public interface CommonItemFeignClient {

    // -------------------------Service/Item-------------------------
    @GetMapping(value = "/service/item/selectTbItemAllByPage")
    public PageResult selectTbItemAllByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows);

    @PostMapping("/service/item/insertTbItem")
    public Integer insertTbItem(@RequestBody TbItem tbItem);

    @PostMapping("/service/item/deleteItemById")
    public Integer deleteItemById(@RequestBody TbItem tbItem);

    @PostMapping("/service/item/preUpdateItem")
    public Map<String, Object> preUpdateItem(@RequestParam("itemId") Long itemId);

    @PostMapping("/service/item/updateTbitem")
    public Integer updateTbitem(@RequestBody TbItem tbItem);

    // -------------------------Service/Category-------------------------
    @PostMapping("/service/itemCategory/selectItemCategoryByParentId")
    public List<TbItemCat> selectItemCategoryByParentId(@RequestParam("id") Long id);

    // -------------------------Service/ItemParam-------------------------
    @PostMapping("/service/itemParam/selectItemParamByItemCatId")
    public TbItemParam selectItemParamByItemCatId(@RequestParam("itemCatId") Long itemCatId);

    @PostMapping("/service/itemParam/selectItemParamAll")
    public PageResult selectItemParamAll(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows);

    @PostMapping("/service/itemParam/insertItemParam")
    public Integer insertItemParam(@RequestBody TbItemParam tbItemParam);

    @PostMapping("/service/itemParam/deleteItemParamById")
    public Integer deleteItemParamById(@RequestParam("id") Long id);

    // -------------------------Service/ItemDesc-------------------------
    @PostMapping("/service/itemDesc/insertItemDesc")
    public Integer insertItemDesc(@RequestBody TbItemDesc tbItemDesc);

    @PostMapping("/service/itemDesc/updateItemDesc")
    public Integer updateItemDesc(@RequestBody TbItemDesc tbItemDesc);

    // -------------------------Service/ItemParamItem-------------------------
    @PostMapping("/service/itemParamItem/insertTbItemParamItem")
    public Integer insertTbItemParamItem(@RequestBody TbItemParamItem tbItemParamItem);

    @PostMapping("/service/itemParamItem/updateItemParamItem")
    public Integer updateItemParamItem(@RequestBody TbItemParamItem tbItemParamItem);
}
