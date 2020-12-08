package com.bjsxt.search.controller;

import com.bjsxt.search.service.SolrService;
import com.bjsxt.utils.Result;
import com.bjsxt.utils.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SolrController {

    @Autowired
    private SolrService solrService;

    /**
     * 向Solr索引库中倒入数据
     */
    @RequestMapping("/importAll")
    public Result importAll() {
        return solrService.importAll();
    }

    /**
     * 搜索数据
     */
    @RequestMapping("/list")
    public List<SolrDocument> selectByq(String q, @RequestParam(defaultValue = "1") Long page, @RequestParam(defaultValue = "10") Integer pageSize) {
        try {
            return solrService.selectByq(q, page, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<SolrDocument> list = new ArrayList<>();
        SolrDocument solrDocument = new SolrDocument();
        solrDocument.setId("536563");
        solrDocument.setItem_title("new2 - 阿尔卡特 (OT-927) 炭黑 联通3G手机 双卡双待");
        solrDocument.setItem_category_name("手机");
        solrDocument.setItem_desc("1111");
        solrDocument.setItem_image("");
        solrDocument.setItem_price("111");
        solrDocument.setItem_sell_point("md");
        list.add(solrDocument);
        return list;
    }


}
