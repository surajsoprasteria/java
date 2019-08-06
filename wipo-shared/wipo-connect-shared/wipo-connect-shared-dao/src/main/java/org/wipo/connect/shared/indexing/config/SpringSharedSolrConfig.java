/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Connect.
 *
 *
 * @author Fincons
 *
 */

package org.wipo.connect.shared.indexing.config;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.RequestMethod;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.HttpSolrClientFactoryBean;

@Configuration
@EnableSolrRepositories
public class SpringSharedSolrConfig {

    @Value("#{configProperties['solr.base-url']}")
    private String solrBaseUrl;

    @Bean
    public HttpSolrClientFactoryBean solrServerFactoryBean() {
	HttpSolrClientFactoryBean factory = new HttpSolrClientFactoryBean();
	factory.setUrl(solrBaseUrl);
	return factory;
    }

    @Bean
    public SolrOperations solrTemplate() throws Exception {
	SolrTemplate solrTemplate = new SolrTemplate(solrClient(), RequestMethod.POST);
	return solrTemplate;
    }

    @Bean
    public SolrClient solrClient() throws Exception {
	return solrServerFactoryBean().getObject();
    }

}
