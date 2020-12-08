package com.bjsxt.content.controller;

import com.bjsxt.content.service.ContentService;
import com.bjsxt.pojo.TbContent;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping("/selectTbContentAllByCategoryId")
    public Result selectTbContentAllByCategoryId(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "30") Integer rows, @RequestParam Long categoryId) {
        try {
            return contentService.selectTbContentAllByCategoryId(page, rows, categoryId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }

    /**
     * 根据分类添加内容
     */
    @RequestMapping("/insertTbContent")
    public Result insertTbContent(TbContent tbContent) {
        try {
            return contentService.insertTbContent(tbContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }

    /**
     * 删除分类的内容
     */
    @RequestMapping("/deleteContentByIds")
    public Result deleteContentByIds(Long ids) {
        try {
            return contentService.deleteContentByIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("error");
    }

}
