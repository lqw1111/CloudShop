package com.bjsxt.backend.item.service.Impl;

import com.bjsxt.backend.item.feign.CommonItemFeignClient;
import com.bjsxt.backend.item.service.ItemParamService;
import com.bjsxt.pojo.TbItemParam;
import com.bjsxt.utils.PageResult;
import com.bjsxt.utils.Result;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;
    @Override
    public Result selectItemParamByItemCatId(Long itemCatId) {
        TbItemParam tbItemParam = this.commonItemFeignClient.selectItemParamByItemCatId(itemCatId);
        if (tbItemParam != null) {
            return Result.ok(tbItemParam);
        }
        return Result.error("查无结果");
    }

    @Override
    public Result selectItemParamAll(Integer page, Integer rows) {
        PageResult pageResult = this.commonItemFeignClient.selectItemParamAll(page, rows);
        if (pageResult != null && pageResult.getResult().size() > 0) {
            return Result.ok(pageResult);
        }
        return Result.error("查无结果");
    }

    /**
     * 添加商品分类规格参数模版
     * @param itemCatId
     * @param paramData
     * @return
     */
    @Override
    @LcnTransaction
    public Result insertItemParam(Long itemCatId, String paramData) {
        //创建TbItemParam对象并补齐数据
        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(itemCatId);
        tbItemParam.setParamData(paramData);
        tbItemParam.setCreated(new Date());
        tbItemParam.setUpdated(new Date());

        Integer itemParamNum = this.commonItemFeignClient.insertItemParam(tbItemParam);
        if (itemParamNum != null) {
            return Result.ok();
        }
        return Result.error("添加失败");
    }

    /**
     * 删除商品规格参数
     * @param id
     * @return
     */
    @Override
    public Result deleteItemParamById(Long id) {
        Integer itemParamNum = this.commonItemFeignClient.deleteItemParamById(id);
        if (itemParamNum != null && itemParamNum > 0) {
            return Result.ok();
        }
        return Result.error("删除失败");
    }
}
