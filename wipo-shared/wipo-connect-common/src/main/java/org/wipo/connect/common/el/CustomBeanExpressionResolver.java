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
package org.wipo.connect.common.el;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.beans.factory.config.BeanExpressionResolver;
import org.springframework.context.expression.StandardBeanExpressionResolver;

public class CustomBeanExpressionResolver extends StandardBeanExpressionResolver implements BeanExpressionResolver {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(CustomBeanExpressionResolver.class);

    private static final String REGEX_ENV = "#\\{ENV:(.+?)}";

    private final Map<String, String> envMap;

    public CustomBeanExpressionResolver() {
	super();
	this.envMap = new CaseInsensitiveMap<>(System.getenv());
    }

    @Override
    public Object evaluate(String value, BeanExpressionContext evalContext) throws BeansException {
	Object outValue;

	String curValue = value;

	curValue = replaceEnvVariables(curValue);
	outValue = super.evaluate(curValue, evalContext);

	if (outValue != null && outValue instanceof String) {
	    outValue = replaceEnvVariables((String) outValue);

	    if (!StringUtils.equals((String) outValue, value)) {
		outValue = this.evaluate((String) outValue, evalContext);
	    }

	}

	LOGGER.trace("Resolve expression: {} -> {}", value, outValue);

	return outValue;
    }

    private String replaceEnvVariables(String expression) {
	String strOut = expression;
	Map<String, String> replaceMap = new HashMap<>();

	Pattern pattern = Pattern.compile(REGEX_ENV);
	Matcher matcher = pattern.matcher(expression);

	while (matcher.find()) {
	    String envVar = matcher.group(1);
	    String key = matcher.group();
	    String value = StringUtils.defaultString(envMap.get(envVar));
	    replaceMap.put(key, value);
	}

	for (Entry<String, String> e : replaceMap.entrySet()) {
	    String key = e.getKey();
	    String value = e.getValue();
	    strOut = StringUtils.replace(strOut, key, value);
	}

	return strOut;
    }

}
