package com.bjsxt.front.portal.service.Impl;

import com.bjsxt.front.portal.feign.CommonItemFeignClient;
import com.bjsxt.front.portal.feign.CommonRedisFeignClient;
import com.bjsxt.front.portal.service.ItemCategoryService;
import com.bjsxt.utils.CatResult;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Autowired
    private CommonRedisFeignClient commonRedisFeignClient;

    @Override
    public Result selectItemCategoryAll() {
        //查询缓存
        try {
            CatResult catResult = this.commonItemFeignClient.selectItemCategoryAll();
            //判断缓存是否命中
            if (catResult != null && catResult.getData() != null && catResult.getData().size() > 0) {
                return Result.ok(catResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //查询数据库
        CatResult catResult = this.commonItemFeignClient.selectItemCategoryAll();
        //添加到缓存
        try {
            if (catResult != null && catResult.getData() != null && catResult.getData().size() > 0) {
                this.commonRedisFeignClient.insertItemCategory(catResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (catResult != null && catResult.getData() != null && catResult.getData().size() > 0) {
            return Result.ok(catResult);
        }
        //添加到缓存
        return Result.error("查无结果");
    }
}
