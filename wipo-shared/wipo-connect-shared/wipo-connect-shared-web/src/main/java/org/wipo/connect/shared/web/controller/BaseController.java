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

package org.wipo.connect.shared.web.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.wipo.connect.common.conversion.JacksonObjectMapper;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.querypagination.Column;
import org.wipo.connect.common.querypagination.Order;
import org.wipo.connect.common.querypagination.PageInfo;
import org.wipo.connect.shared.exchange.dto.impl.UiLanguage;
import org.wipo.connect.shared.web.authentication.SecurityUserDetail;
import org.wipo.connect.shared.web.conversion.DateConversionUtils;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * The Class BaseController.
 */
public class BaseController {

    /** The logger. */
    protected static final Logger LOGGER = WipoLoggerFactory.getLogger(BaseController.class);

    @Autowired
    protected DateConversionUtils dateConversionUtils;

    @Autowired
    private JacksonObjectMapper jacksonObjectMapper;

    @Autowired
    @Qualifier("configProperties")
    protected Properties configProperties;

    protected Authentication getAuthentication() {
	return SecurityContextHolder.getContext().getAuthentication();
    }

    protected boolean isAuthenticated() {
	return getAuthentication().isAuthenticated();
    }

    protected Locale getCurrentLocale() {
	return LocaleContextHolder.getLocale();
    }

    protected Long getCurrentLanguageId() {
	Authentication auth = getAuthentication();

	if (auth == null || !auth.isAuthenticated()) {
	    return null;
	}

	Object principal = auth.getPrincipal();

	if (!(principal instanceof SecurityUserDetail)) {
	    return null;
	}

	UiLanguage uiLanguage = ((SecurityUserDetail) principal).getUiLanguage();
	if (uiLanguage != null && uiLanguage.getId() != null) {
	    return uiLanguage.getFkLanguage();
	} else {
	    return null;
	}

    }

    protected PageInfo extractPageInfo(HttpServletRequest req) {
	PageInfo pageInfo = new PageInfo();
	boolean foundDraw = false;
	boolean foundStart = false;
	boolean foundLength = false;
	for (Entry<String, String[]> entry : req.getParameterMap().entrySet()) {
	    String key = entry.getKey();
	    String value = null;
	    if (entry.getValue() != null && entry.getValue().length > 0) {
		value = entry.getValue()[0];
	    }

	    if (StringUtils.equals(key, "draw")) {
		foundDraw = true;
		pageInfo.setDraw(NumberUtils.createInteger(value));
	    } else if (StringUtils.equals(key, "start")) {
		foundStart = true;
		pageInfo.setStart(NumberUtils.createInteger(value));
	    } else if (StringUtils.equals(key, "length")) {
		foundLength = true;
		pageInfo.setLength(NumberUtils.createInteger(value));
	    } else if (StringUtils.startsWith(key, "order[")) {
		Pattern pattern;
		Matcher matcher;

		pattern = Pattern.compile("^order\\[(\\d+)\\][\\[|.]column\\]?$");
		matcher = pattern.matcher(key);
		if (matcher.find()) {
		    int idx = NumberUtils.createInteger(matcher.group(1));
		    Order order = pageInfo.getOrder().get(idx);
		    order.setColumn(NumberUtils.createInteger(value));
		} else {
		    pattern = Pattern.compile("^order\\[(\\d+)\\][\\[|.]dir\\]?$");
		    matcher = pattern.matcher(key);
		    if (matcher.find()) {
			int idx = NumberUtils.createInteger(matcher.group(1));
			Order order = pageInfo.getOrder().get(idx);
			order.setDir(value);
		    }
		}
	    } else if (StringUtils.startsWith(key, "columns[")) {
		Pattern pattern;
		Matcher matcher;

		pattern = Pattern.compile("^columns\\[(\\d+)\\][\\[|.]data\\]?$");
		matcher = pattern.matcher(key);
		if (matcher.find()) {
		    int idx = NumberUtils.createInteger(matcher.group(1));
		    Column column = pageInfo.getColumns().get(idx);
		    column.setData(value);
		} else {
		    pattern = Pattern.compile("^columns\\[(\\d+)\\][\\[|.]name\\]?$");
		    matcher = pattern.matcher(key);
		    if (matcher.find()) {
			int idx = NumberUtils.createInteger(matcher.group(1));
			Column column = pageInfo.getColumns().get(idx);
			column.setName(value);
		    }
		}
	    }

	}

	if (foundDraw && foundStart && foundLength) {
	    return pageInfo;
	} else {
	    return null;
	}

    }

    protected <T extends Object> String getAsJson(T obj) {
	try {
	    return jacksonObjectMapper.writeValueAsString(obj);
	} catch (JsonProcessingException e) {
	    return "[]";
	}
    }

    @SuppressWarnings("unchecked")
    protected Map<String, Object> getAsMap(Object obj) {
	try {
	    return jacksonObjectMapper.convertValue(obj, Map.class);
	} catch (IllegalArgumentException e) {
	    return new HashMap<>();
	}
    }

}