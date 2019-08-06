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
package org.wipo.connect.common.restclient;


import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * The Class BasicAuthRestTemplate.
 */
public class BasicAuthRestTemplate extends RestTemplate {

	private static final Integer DEFAULT_MAX_TOTAL = 255;
	private static final Integer DEFAULT_MAX_PER_ROUTE = 255;


	/**
	 * Instantiates a new basic auth rest template.
	 *
	 * @param user the user
	 * @param password the password
	 */
	public BasicAuthRestTemplate(String user, String password) {
		this(user,password,DEFAULT_MAX_TOTAL,DEFAULT_MAX_PER_ROUTE);
	}

	/**
	 * Instantiates a new basic auth rest template.
	 *
	 * @param user the user
	 * @param password the password
	 * @param maxTotal the max total
	 * @param defMaxPerRoute the def max per route
	 */
	public BasicAuthRestTemplate(String user, String password, Integer maxTotal, Integer defMaxPerRoute) {
		PoolingHttpClientConnectionManager syncConnectionManager = new PoolingHttpClientConnectionManager();
		syncConnectionManager.setMaxTotal(maxTotal);
		syncConnectionManager.setDefaultMaxPerRoute(defMaxPerRoute);


		BasicCredentialsProvider credentialsProvider =  new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(user, password));

		HttpRequestInterceptor reqInterceptor = new RestClientRequestInterceptor();

		CloseableHttpClient httpClient = HttpClients.custom()
				.setConnectionManager(syncConnectionManager)
                .setDefaultCredentialsProvider(credentialsProvider)
                .addInterceptorFirst(reqInterceptor)
                .build();

		ClientHttpRequestFactory rf = new HttpComponentsClientHttpRequestFactory(httpClient);

		this.setRequestFactory(rf);
	}

}
