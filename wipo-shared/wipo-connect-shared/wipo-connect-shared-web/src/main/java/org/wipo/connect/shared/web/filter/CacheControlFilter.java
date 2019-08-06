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

package org.wipo.connect.shared.web.filter;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.spring.SpringUtils;

/**
 * The Class CacheControlFilter.
 */
public class CacheControlFilter implements Filter {

    /** The logger. */
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(CacheControlFilter.class);

    /** The chache expires seconds. */
    private Long chacheExpiresSeconds = 86400L; // 1 giorno

    private String[] cacheUrls = { "/res/" };

    @Override
    public void destroy() {
	// empry method
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

	HttpServletResponse httpResponse;
	HttpServletRequest httpRequest;

	try {
	    boolean enableChache = false;
	    httpResponse = (HttpServletResponse) response;
	    httpRequest = (HttpServletRequest) request;

	    LOGGER.trace("CACHE: " + enableChache + " --> " + httpRequest.getRequestURL().toString());

	    for (String url : cacheUrls) {
		enableChache = enableChache || matchUrl(httpRequest, url);
	    }

	    if (enableChache) {
		httpResponse.setDateHeader("Expires", System.currentTimeMillis() + (this.chacheExpiresSeconds * 1000));
		httpResponse.setHeader("Cache-Control", "max-age=" + this.chacheExpiresSeconds + ", s-maxage=" + this.chacheExpiresSeconds + ", must-revalidate");
	    } else {
		httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		httpResponse.setHeader("Pragma", "no-cache");
		httpResponse.setDateHeader("Expires", 0);
		httpResponse.setDateHeader("Last-Modified", System.currentTimeMillis());
	    }

	} catch (Exception e) {
	    LOGGER.error("Errore in doFilter", e);
	    throw new ServletException("Errore in doFilter", e);
	} finally {
	    chain.doFilter(request, response);
	}
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
	String chacheExpiresSecondsStr;
	String chacheUrlsStr;
	try {
	    chacheExpiresSecondsStr = filterConfig.getInitParameter("cache-expires-seconds");
	    if (!StringUtils.isEmpty(chacheExpiresSecondsStr)) {
		this.chacheExpiresSeconds = Long.parseLong(chacheExpiresSecondsStr);
	    }

	    try {
		Properties configProperties = SpringUtils.applicationContext.getBean("configProperties", Properties.class);
		chacheExpiresSecondsStr = configProperties.getProperty("cache.expires-seconds");
		if (!StringUtils.isEmpty(chacheExpiresSecondsStr)) {
		    this.chacheExpiresSeconds = Long.parseLong(chacheExpiresSecondsStr);
		}

		chacheUrlsStr = configProperties.getProperty("cache.urls");
		if (!StringUtils.isEmpty(chacheUrlsStr)) {
		    this.cacheUrls = StringUtils.split(chacheUrlsStr, ",");
		}
	    } catch (Exception e) {
		LOGGER.error("Error reading cache settings from properties file", e);
	    }

	} catch (Exception e) {
	    LOGGER.error("Error in init", e);
	    throw new ServletException("Error in init", e);
	}
    }

    private String getBasePath(HttpServletRequest httpRequest) {
	String scheme = httpRequest.getScheme();
	String serverName = httpRequest.getServerName();
	int serverPort = httpRequest.getServerPort();
	String contextPath = httpRequest.getContextPath(); // includes leading forward slash

	String basePath = scheme + "://" + serverName + ":" + serverPort + contextPath;

	return basePath;
    }

    private boolean matchUrl(HttpServletRequest httpRequest, String match) {
	String basePath = getBasePath(httpRequest);
	String relativePath = StringUtils.removeStart(httpRequest.getRequestURL().toString(), basePath);

	return StringUtils.startsWith(relativePath, match);
    }

}
