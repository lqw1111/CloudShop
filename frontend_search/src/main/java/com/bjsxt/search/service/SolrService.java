package com.bjsxt.search.service;

import com.bjsxt.utils.Result;
import com.bjsxt.utils.SolrDocument;

import java.util.List;

public interface SolrService {
    Result importAll();

    List<SolrDocument> selectByq(String q, Long page, Integer pageSize);
}
