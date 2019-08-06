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
package org.wipo.connect.common.properties;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.map.CaseInsensitiveMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;

public class ResolvableProperties extends Properties {

    private static final long serialVersionUID = 3194962655464097485L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(ResolvableProperties.class);

    private static final String REGEX_ENV = "#\\{ENV:(.+?)}";
    private static final String REGEX_PROP = "#\\{PROP:(.+?)}";

    private final Map<String, String> envMap;

    public ResolvableProperties() {
	super();
	this.envMap = new CaseInsensitiveMap<>(System.getenv());
    }

    public ResolvableProperties(Properties defaults) {
	super(defaults);
	this.envMap = System.getenv();
    }

    @Override
    public String getProperty(String key) {
	String value = super.getProperty(key);
	value = evalExpession(value);
	return value;
    }

    @Override
    public String getProperty(String key, String defaultValue) {
	String value = super.getProperty(key, defaultValue);
	value = evalExpession(value);
	return value;
    }

    @Override
    public synchronized Object get(Object key) {
	String value = (String) super.get(key);
	value = evalExpession(value);
	return value;
    }

    @Override
    public synchronized Object getOrDefault(Object key, Object defaultValue) {
	String value = (String) super.getOrDefault(key, defaultValue);
	value = evalExpession(value);
	return value;
    }

    private String evalExpession(String value) {
	if (StringUtils.isEmpty(value)) {
	    return value;
	}

	value = replaceEnvVariables(value);
	value = replaceNestedProperties(value);

	return value;
    }

    private String replaceNestedProperties(String expression) {
	String strOut = expression;
	Map<String, String> replaceMap = new HashMap<>();

	Pattern pattern = Pattern.compile(REGEX_PROP);
	Matcher matcher = pattern.matcher(expression);

	while (matcher.find()) {
	    String strExpr = matcher.group(1);
	    String key = matcher.group();
	    String value = this.getProperty(strExpr);
	    replaceMap.put(key, value);
	}

	for (Entry<String, String> e : replaceMap.entrySet()) {
	    String key = e.getKey();
	    String value = e.getValue();
	    strOut = StringUtils.replace(strOut, key, value);
	}

	return strOut;
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
