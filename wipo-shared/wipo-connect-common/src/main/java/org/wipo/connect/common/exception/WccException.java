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

package org.wipo.connect.common.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class WccException.
 *
 * @author fumagalli
 */
@SuppressWarnings({ "squid:S1948" })
@JsonIgnoreProperties(ignoreUnknown = true)
public class WccException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2613000375218797726L;

    private final WccExceptionCodeEnum code;
    protected final Map<String, Object> data;
    private final String uuid;

    @JsonCreator
    protected WccException(@JsonProperty("code") WccExceptionCodeEnum code, @JsonProperty("message") String message, @JsonProperty(value = "data", required = false) Map<String, Object> data,
	    @JsonProperty("uuid") String uuid) {
	super(message);
	this.code = code;
	this.data = data;
	this.uuid = uuid;
    }

    public WccException(WccExceptionCodeEnum code, String message, Map<String, Object> data) {
	super(message);
	this.code = code;
	this.uuid = UUID.randomUUID().toString();
	this.data = data;
    }

    public WccException(WccExceptionCodeEnum code, String message, Throwable cause, Map<String, Object> data) {
	super(message, cause);
	this.code = code;
	this.uuid = UUID.randomUUID().toString();
	if (cause instanceof WccException) {
	    Map<String, Object> oldData = ((WccException) cause).getData();
	    Map<String, Object> newData = data;

	    if (oldData == null) {
		oldData = new HashMap<>();
	    }
	    if (newData == null) {
		newData = new HashMap<>();
	    }

	    this.data = new HashMap<>();
	    this.data.putAll(oldData);
	    this.data.putAll(newData);
	} else {
	    this.data = data;
	}
    }

    public WccException(WccExceptionCodeEnum code, Throwable cause, Map<String, Object> data) {
	super(cause);
	this.code = code;
	this.uuid = UUID.randomUUID().toString();
	if (cause instanceof WccException) {
	    Map<String, Object> oldData = ((WccException) cause).getData();
	    Map<String, Object> newData = data;

	    if (oldData == null) {
		oldData = new HashMap<>();
	    }
	    if (newData == null) {
		newData = new HashMap<>();
	    }

	    this.data = new HashMap<>();
	    this.data.putAll(oldData);
	    this.data.putAll(newData);
	} else {
	    this.data = data;
	}
    }

    public WccException(WccExceptionCodeEnum code, String message) {
	this(code, message, new HashMap<String, Object>());
    }

    public WccException(WccExceptionCodeEnum code, String message, Throwable cause) {
	this(code, message, cause, new HashMap<String, Object>());
    }

    public WccException(WccExceptionCodeEnum code, Throwable cause) {
	this(code, cause, new HashMap<String, Object>());
    }

    public WccException(Throwable cause) {
	super(cause);
	if (cause instanceof WccException) {
	    WccException wccCause = (WccException) cause;
	    this.code = wccCause.getCode();
	    this.uuid = UUID.randomUUID().toString();
	    this.data = wccCause.getData();
	} else {
	    this.code = WccExceptionCodeEnum.UNKNOW_ERROR;
	    this.uuid = UUID.randomUUID().toString();
	    this.data = new HashMap<>();
	}
    }

    public WccExceptionCodeEnum getCode() {
	return code;
    }

    public Map<String, Object> getData() {
	return data;
    }

    @Override
    public String toString() {
	return "[" + uuid + "] " + super.toString();
    }

    public String getUuid() {
	return uuid;
    }

}
