package com.bjsxt.search.service.Impl;

import com.bjsxt.mapper.SolrItemMapper;
import com.bjsxt.pojo.SolrItem;
import com.bjsxt.search.service.SolrService;
import com.bjsxt.utils.Result;
import com.bjsxt.utils.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.HighlightQuery;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolrServiceImpl implements SolrService {

    @Value("${spring.data.solr.core}")
    private String collection;

    @Autowired
    private SolrItemMapper solrItemMapper;

    @Autowired
    private SolrTemplate solrTemplate;

    /**
     * 索引库中倒入数据
     * @return
     */
    @Override
    public Result importAll() {
        //查询数据
        try {
            List<SolrItem> list= this.solrItemMapper.getItemList();

            for (SolrItem solrItem : list) {
                //创建一个SolrInputDocument对象
                //添加数据模型
                SolrInputDocument document = new SolrInputDocument();
                document.setField("id", solrItem.getId());
                document.setField("item_title", solrItem.getTitle());
                document.setField("item_sell_point", solrItem.getSell_point());
                document.setField("item_price", solrItem.getPrice());
                document.setField("item_image", solrItem.getImage());
                document.setField("item_category_name", solrItem.getName());
                document.setField("item_desc", solrItem.getItem_desc());
                //写入索引库
                this.solrTemplate.saveDocument(collection, document);
            }
            this.solrTemplate.commit(collection);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.error("error");
    }

    @Override
    public List<SolrDocument> selectByq(String q, Long page, Integer pageSize) {
        //设置高亮查询条件
        HighlightQuery query = new SimpleHighlightQuery();
        Criteria criteria = new Criteria("item_keywords");
        criteria.is(q);
        query.addCriteria(criteria);

        //设置高亮属性
        HighlightOptions highlightOptions = new HighlightOptions();
        highlightOptions.addField("item_title");//设置高亮显示的域
        highlightOptions.setSimplePrefix("<em style='color:red'>");//设置高亮的样式
        highlightOptions.setSimplePostfix("</em>");
        query.setHighlightOptions(highlightOptions);

        query.setOffset((page - 1) * pageSize);
        query.setRows(pageSize);

        HighlightPage<SolrDocument> highlightPage = this.solrTemplate.queryForHighlightPage(this.collection, query, SolrDocument.class);

        List<HighlightEntry<SolrDocument>> highlighted = highlightPage.getHighlighted();

        for (HighlightEntry<SolrDocument> tbItemHighlightEntry : highlighted) {
            SolrDocument entity = tbItemHighlightEntry.getEntity(); //实体对象
            List<HighlightEntry.Highlight> highlights = tbItemHighlightEntry.getHighlights();
            //如果有高亮，就取高亮
            if (highlights != null && highlights.size() > 0 && highlights.get(0).getSnipplets().size() > 0) {
                entity.setItem_title(highlights.get(0).getSnipplets().get(0));
            }
        }

        List<SolrDocument> list = highlightPage.getContent();
        return list;
    }
}
