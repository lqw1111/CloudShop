package com.bjsxt.common.redis.controller;

import com.bjsxt.common.redis.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/redis/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    /**
     * 打广告位数据添加到缓存
     */
    @RequestMapping("/insertContentAD")
    public void insertContentAD(@RequestBody List<Map> list){
        this.contentService.insertContentAD(list);
    }

    /**
     * 查询大广告位数据
     */
    @RequestMapping("/selectContentAD")
    public List<Map> selectContentAD(){
        return contentService.selectContentAD();
    }
}
