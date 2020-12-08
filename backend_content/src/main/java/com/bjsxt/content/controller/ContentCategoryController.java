package com.bjsxt.content.controller;

import com.bjsxt.content.service.ContentCategoryService;
import com.bjsxt.pojo.TbContentCategory;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    /**
     * 根据当前节点id查询子节点
     */
    @RequestMapping("/selectContentCategoryByParentId")
    public Result selectContentCategoryByParentId(@RequestParam(name = "id", defaultValue = "0") Long id) {
        try {
            return contentCategoryService.selectContentCategoryByParentId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }

    /**
     * 添加内容分类
     */
    @RequestMapping("/insertContentCategory")
    public Result insertContentCategory(TbContentCategory tbContentCategory) {
        try {
            return contentCategoryService.insertContentCategory(tbContentCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }

    /**
     * 删除内容分类
     */
    @RequestMapping("/deleteContentCategoryById")
    public Result deleteContentCategoryById(Long categoryId) {
        try {
            return contentCategoryService.deleteContentCategoryById(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }

    /**
     * 修改内容分类
     */
    @RequestMapping("/updateContentCategory")
    public Result updateContentCategory(TbContentCategory tbContentCategory) {
        try {
            return contentCategoryService.updateContentCategory(tbContentCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }

}
