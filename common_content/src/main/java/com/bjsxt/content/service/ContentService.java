package com.bjsxt.content.service;

import com.bjsxt.pojo.TbContent;
import com.bjsxt.utils.PageResult;

import java.util.List;
import java.util.Map;

public interface ContentService {
    PageResult selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId);

    Integer insertTbContent(TbContent tbContent);

    Integer deleteContentByIds(Long id);

    List<Map> selectFrontendContentByAD();

}
