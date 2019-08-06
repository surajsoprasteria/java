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

package org.wipo.connect.ruleengine.rules.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * The Class ValueMap.
 */
public class ValueMap extends HashMap<String, Object> implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -8088788136065639666L;

    /** The skip engine initialize. */
    private boolean skipEngineInitialize;

    /**
     * Instantiates a new value map.
     */
    public ValueMap() {
	super();
    }

    /**
     * Instantiates a new value map.
     *
     * @param map
     *            the map
     */
    public ValueMap(Map<? extends String, ? extends Object> map) {
	super(map);
    }

    public ValueMap(Object... pairs) {
	HashMap<String, Object> map = new HashMap<>();
	for (int i = 0; i < pairs.length; i += 2) {
	    map.put(pairs[i].toString(), pairs[i + 1]);
	}
	this.putAll(map);
    }

    /**
     * Adds the.
     *
     * @param rule
     *            the rule
     */
    public void add(Rule rule) {
	if (rule == null || StringUtils.isEmpty(rule.getName())) {
	    throw new IllegalArgumentException("Rule is incomplete:" + rule);
	}
	put(rule.getName(), rule);
    }

    /**
     * Gets the input parameters.
     *
     * @return the input parameters
     */
    public Map<String, Object> getInputParameters() {
	Map<String, Object> pars = new HashMap<>();
	for (String key : keySet()) {
	    Object value = get(key);
	    if (value != null && !(value instanceof Rule) && !(value instanceof List)) {
		pars.put(key, value);
	    }
	}
	return pars;
    }

    /**
     * Gets the parameters.
     *
     * @return the parameters
     */
    public Map<String, Object> getParameters() {
	Map<String, Object> pars = new HashMap<>();
	for (String key : keySet()) {
	    Object value = get(key);
	    if (value != null && !(value instanceof Rule)) {
		pars.put(key, value);
	    }
	}
	return pars;
    }

    /**
     * Gets the rules.
     *
     * @return the rules
     */
    public Map<String, Object> getRules() {
	Map<String, Object> rules = new HashMap<>();
	for (String key : keySet()) {
	    Object value = get(key);
	    if (value != null && (value instanceof Rule)) {
		rules.put(key, value);
	    }
	}
	return rules;
    }

    /**
     * Checks if is skip engine initialize.
     *
     * @return true, if is skip engine initialize
     */
    public boolean isSkipEngineInitialize() {
	return this.skipEngineInitialize;
    }

    /**
     * Sets the skip engine initialize.
     *
     * @param skipEngineInitialize
     *            the new skip engine initialize
     */
    public void setSkipEngineInitialize(boolean skipEngineInitialize) {
	this.skipEngineInitialize = skipEngineInitialize;
    }
}
