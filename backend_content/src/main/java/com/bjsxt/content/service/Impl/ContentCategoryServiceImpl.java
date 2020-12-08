package com.bjsxt.content.service.Impl;

import com.bjsxt.content.feign.CommonContentFeignClient;
import com.bjsxt.content.service.ContentCategoryService;
import com.bjsxt.pojo.TbContentCategory;
import com.bjsxt.utils.Result;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private CommonContentFeignClient commonContentFeignClient;

    @Override
    public Result selectContentCategoryByParentId(Long id) {
        List<TbContentCategory> list = this.commonContentFeignClient.selectContentCategoryByParentId(id);
        if (list != null && list.size() > 0) {
            return Result.ok(list);
        }
        return Result.error("查无结果");
    }

    @Override
    @LcnTransaction
    public Result insertContentCategory(TbContentCategory tbContentCategory) {
        Integer contentCategoryNum = this.commonContentFeignClient.insertContentCategory(tbContentCategory);
        if (contentCategoryNum != null) {
            return Result.ok();
        }
        return Result.error("添加失败");
    }

    @Override
    @LcnTransaction
    public Result deleteContentCategoryById(Long categoryId) {
        Integer status = this.commonContentFeignClient.deleteContentCategoryById(categoryId);
        if (status == 200) {
            return Result.ok();
        }
        return Result.error("删除失败");
    }

    @Override
    @LcnTransaction
    public Result updateContentCategory(TbContentCategory tbContentCategory) {
        Integer num = this.commonContentFeignClient.updateContentCategory(tbContentCategory);
        if (num != null) {
            return Result.ok();
        }
        return Result.error("更新失败");
    }
}
