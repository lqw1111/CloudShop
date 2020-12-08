package com.bjsxt.content.feign;

import com.bjsxt.pojo.TbContent;
import com.bjsxt.pojo.TbContentCategory;
import com.bjsxt.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "common-content")
public interface CommonContentFeignClient {

    // --------------------------/Service/ContentCategory--------------------------/
    @PostMapping("/service/contentCategory/selectContentCategoryByParentId")
    List<TbContentCategory> selectContentCategoryByParentId(@RequestParam("parentId") Long parentId);

    @PostMapping("/service/contentCategory/insertContentCategory")
    Integer insertContentCategory(@RequestBody TbContentCategory tbContentCategory);

    @PostMapping("/service/contentCategory/deleteContentCategoryById")
    public Integer deleteContentCategoryById(@RequestParam("categoryId") Long categoryId);

    @PostMapping("/service/contentCategory/updateContentCategory")
    public Integer updateContentCategory(@RequestBody TbContentCategory tbContentCategory);

    // --------------------------/Service/content--------------------------/
    @PostMapping("/service/content/selectTbContentAllByCategoryId")
    public PageResult selectTbContentAllByCategoryId(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("categoryId") Long categoryId);

    @PostMapping("/service/content/insertTbContent")
    public Integer insertTbContent(@RequestBody TbContent tbContent);

    @PostMapping("/service/content/deleteContentByIds")
    public Integer deleteContentByIds(@RequestParam("id") Long id);
}
