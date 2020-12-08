package com.bjsxt.backend.item.service.Impl;

import com.bjsxt.backend.item.feign.CommonItemFeignClient;
import com.bjsxt.backend.item.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.utils.IDUtils;
import com.bjsxt.utils.PageResult;
import com.bjsxt.utils.Result;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Map;

/**
 * 商品管理
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Override
    public Result selectTbItemAllByPage(Integer page, Integer rows) {
        PageResult pageResult = this.commonItemFeignClient.selectTbItemAllByPage(page, rows);
        if (pageResult != null && pageResult.getResult() != null && pageResult.getResult().size() > 0) {
            return Result.ok(pageResult);
        }
        return Result.error("查无结果");
    }

    /**
     * 添加tbItem, 添加TbItemDesc, 添加itemParams
     * @param tbItem
     * @param desc
     * @param itemParams
     * @return
     */
    @Override
    @LcnTransaction
    public Result insertTbItem(TbItem tbItem, String desc, String itemParams) throws RuntimeException{
        //补齐tbItem数据
        Long itemId = IDUtils.genItemId();
        Date date = new Date();
        tbItem.setId(itemId);
        tbItem.setStatus((byte)1);
        tbItem.setUpdated(date);
        tbItem.setCreated(date);
        Integer tbItemNum = this.commonItemFeignClient.insertTbItem(tbItem);

        //补齐商品描述
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        Integer tbItemDescNum = this.commonItemFeignClient.insertItemDesc(tbItemDesc);

        //补齐商品规格参数
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setItemId(itemId);
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setUpdated(date);
        tbItemParamItem.setCreated(date);
        Integer itemParamItemNum = this.commonItemFeignClient.insertTbItemParamItem(tbItemParamItem);

        if (tbItemNum == null || tbItemDescNum == null || itemParamItemNum == null) {
            throw new RuntimeException();
        }

        return Result.ok();
    }

    @Override
    public Result deleteItemById(Long itemId) {
        TbItem tbItem = new TbItem();
        tbItem.setId(itemId);
        tbItem.setStatus((byte)3);
        Integer itemNum = this.commonItemFeignClient.deleteItemById(tbItem);
        if (itemNum != null) {
            return Result.ok(itemNum);
        }
        return Result.error("删除失败");
    }

    @Override
    public Result preUpdateItem(Long itemId) {
        Map<String, Object> map = this.commonItemFeignClient.preUpdateItem(itemId);
        if (map != null) {
            return Result.ok(map);
        }
        return Result.error("查无结果");
    }

    /**
     * 更新商品：更新TbItem表，更新TbItemDesc表
     * @param tbItem
     * @param desc
     * @param itemParams
     * @return
     */
    @Override
    @LcnTransaction
    public Result updateTbItem(TbItem tbItem, String desc, String itemParams) {
        //商品更新
        Integer itemNum = this.commonItemFeignClient.updateTbitem(tbItem);

        //更新商品描述
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(tbItem.getId());
        tbItemDesc.setItemDesc(desc);
        Integer itemSescNum = this.commonItemFeignClient.updateItemDesc(tbItemDesc);

        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setItemId(tbItem.getId());
        Integer itemParamItemNum = this.commonItemFeignClient.updateItemParamItem(tbItemParamItem);

        if (itemNum == null || itemSescNum == null || itemParamItemNum == null) {
            throw new RuntimeException();
        }

        return Result.ok();
    }
}
