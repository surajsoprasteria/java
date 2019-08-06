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
package org.wipo.connect.common.report.bean;

import java.io.Serializable;

/**
 * The Class ParamEntry.
 */
@SuppressWarnings({"squid:S1948","squid:S1165"})
public class ParamEntry implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -2585417976403476873L;

	private String key;
	private Object value;

	/**
	 * Instantiates a new param entry.
	 */
	public ParamEntry(){};
	
	/**
	 * Instantiates a new param entry.
	 *
	 * @param key the key
	 * @param value the value
	 */
	public ParamEntry(String key, Object value){
		setKey(key);
		setValue(value);
	};

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}
	
	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(Object value) {
		this.value = value;
	}


}
