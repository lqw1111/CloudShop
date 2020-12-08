package com.bjsxt.search.config;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;

/**
 * solr配置类
 */
@Configuration
public class SolrConfig {

    @Autowired
    private SolrClient solrClient;

    @Bean
    public SolrTemplate getSolrTemplate() {
        return new SolrTemplate(solrClient);
    }
}
