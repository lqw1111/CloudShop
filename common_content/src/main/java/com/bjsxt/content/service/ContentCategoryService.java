package com.bjsxt.content.service;

import com.bjsxt.pojo.TbContentCategory;

import java.util.List;

public interface ContentCategoryService {
    List<TbContentCategory> selectContentCategoryByParentId(Long parentId);

    Integer insertContentCategory(TbContentCategory tbContentCategory);

    Integer deleteContentCategoryById(Long categoryId);

    Integer updateContentCategory(TbContentCategory tbContentCategory);
}
