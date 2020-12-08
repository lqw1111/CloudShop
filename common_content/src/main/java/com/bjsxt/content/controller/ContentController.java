package com.bjsxt.content.controller;

import com.bjsxt.content.service.ContentService;
import com.bjsxt.pojo.TbContent;
import com.bjsxt.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * 根据内容分类查询内容
     */
    @RequestMapping("/selectTbContentAllByCategoryId")
    public PageResult selectTbContentAllByCategoryId(@RequestParam Integer page, @RequestParam Integer rows, @RequestParam Long categoryId) {
        return contentService.selectTbContentAllByCategoryId(page, rows, categoryId);
    }

    /**
     * 根据分类添加内容
     */
    @RequestMapping("/insertTbContent")
    public Integer insertTbContent(@RequestBody TbContent tbContent) {
        return contentService.insertTbContent(tbContent);
    }

    /**
     * 根据Id删除内容
     */
    @PostMapping("/deleteContentByIds")
    public Integer deleteContentByIds(@RequestParam("id") Long id) {
        return contentService.deleteContentByIds(id);
    }

    /**
     * 查询首页打广告位
     */
    @RequestMapping("/selectFrontendContentByAD")
    public List<Map> selectFrontendContentByAD() {
        return contentService.selectFrontendContentByAD();
    }
}
