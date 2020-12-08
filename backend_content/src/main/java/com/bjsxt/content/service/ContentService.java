package com.bjsxt.content.service;

import com.bjsxt.pojo.TbContent;
import com.bjsxt.utils.Result;

public interface ContentService {
    Result selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId);

    Result insertTbContent(TbContent tbContent);

    Result deleteContentByIds(Long id);
}
