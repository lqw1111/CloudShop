package com.bjsxt.front.portal.feign;

import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.utils.CatResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "common-item")
public interface CommonItemFeignClient {


    /* /service/ItemCategory */
    @PostMapping("/service/itemCategory/selectItemCategoryAll")
    CatResult selectItemCategoryAll();

    // --------------/service/item
    @PostMapping("/service/item/selectItemInfo")
    TbItem selectItemInfo(@RequestParam("itemId") Long itemId);

    @PostMapping("/service/itemDesc/selectItemDescByItemId")
    TbItemDesc selectItemDescByItemId(@RequestParam("itemId") Long itemId);

    @PostMapping("/service/itemParamItem/selectTbItemParamItemByItemId")
    TbItemParamItem selectTbItemParamItemByItemId(@RequestParam("itemId") Long itemId);
}
