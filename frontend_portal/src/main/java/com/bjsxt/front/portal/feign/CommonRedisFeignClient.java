package com.bjsxt.front.portal.feign;

import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.utils.CatResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "common-redis")
public interface CommonRedisFeignClient {

    @PostMapping("/redis/itemCategory/insertItemCategory")
    void insertItemCategory(@RequestBody CatResult catResult);

    @PostMapping("/redis/itemCategory/selectItemCategory")
    CatResult selectItemCategory();


    @PostMapping("/redis/content/insertContentAD")
    void insertContentAD(@RequestBody List<Map> list);

    @PostMapping("/redis/content/selectContentAD")
    List<Map> selectContentAD();

    //----------/redis/item
    @PostMapping("/redis/item/insertItemBasicInfo")
    void insertItemBasicInfo(@RequestBody TbItem tbItem);

    @PostMapping("/redis/item/selectItemBasicInfo")
    TbItem selectItemBasicInfo(@RequestParam("tbItemId") Long tbItemId);

    @PostMapping("/redis/item/insertItemDesc")
    void insertItemDesc(@RequestBody TbItemDesc tbItemDesc);

    @PostMapping("/redis/item/selectItemDesc")
    TbItemDesc selectItemDesc(@RequestParam("tbItemId") Long tbItemId);

    @PostMapping("/redis/item/insertItemParamItem")
    void insertItemParamItem(@RequestBody TbItemParamItem tbItemParamItem);

    @PostMapping("/redis/item/selectItemParamItem")
    TbItemParamItem selectItemParamItem(@RequestParam("tbItemId") Long tbItemId);
}
