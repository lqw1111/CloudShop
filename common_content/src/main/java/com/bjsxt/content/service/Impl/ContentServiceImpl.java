package com.bjsxt.content.service.Impl;

import com.bjsxt.content.service.ContentService;
import com.bjsxt.mapper.TbContentMapper;
import com.bjsxt.pojo.TbContent;
import com.bjsxt.pojo.TbContentExample;
import com.bjsxt.utils.PageResult;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Value("${frontend.AD}")
    private Long AD;

    @Override
    public PageResult selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId) {
        PageHelper.startPage(page, rows);
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> list = this.tbContentMapper.selectByExample(example);
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);

        PageResult result = new PageResult();
        result.setPageIndex(page);
        result.setTotalPage(pageInfo.getTotal());
        result.setResult(pageInfo.getList());

        return result;
    }

    @Override
    @LcnTransaction
    public Integer insertTbContent(TbContent tbContent) {
        tbContent.setUpdated(new Date());
        tbContent.setCreated(new Date());
        return this.tbContentMapper.insertSelective(tbContent);
    }

    @Override
    public Integer deleteContentByIds(Long id) {
        return this.tbContentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Map> selectFrontendContentByAD() {
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(this.AD);
        List<TbContent> list = this.tbContentMapper.selectByExample(tbContentExample);
        List<Map> result = new ArrayList<>();

        for (TbContent content : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("heightB", 240);
            map.put("src", content.getPic());
            map.put("width", 670);
            map.put("alt", content.getSubTitle());
            map.put("srcB", null);
            map.put("widthB", 550);
            map.put("href", content.getUrl());
            map.put("height", 240);
            result.add(map);
        }
        return result;
    }
}
