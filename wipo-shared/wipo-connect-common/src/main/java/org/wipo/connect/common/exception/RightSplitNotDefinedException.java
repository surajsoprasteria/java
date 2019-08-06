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

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class RightSplitNotDefinedException.
 */
@JsonIgnoreProperties(ignoreUnknown = true, value = { "cause", "stackTrace", "defaultResourceBundleName" })
public class RightSplitNotDefinedException extends WccException {

    private static final long serialVersionUID = -1168552210017961948L;

    @JsonCreator
    protected RightSplitNotDefinedException(@JsonProperty(value = "code", required = true) WccExceptionCodeEnum code, @JsonProperty(value = "message", required = false) String message,
	    @JsonProperty(value = "data", required = false) Map<String, Object> data, @JsonProperty(value = "uuid", required = true) String uuid) {
	super(code, message, data, uuid);
    }

    /**
     * Instantiates a new right split not defined exception.
     *
     * @param message
     *            the message
     */
    public RightSplitNotDefinedException(String message) {
	super(WccExceptionCodeEnum.VALIDATION_ERROR, message);
    }

    /**
     * Instantiates a new right split not defined exception.
     *
     * @param message
     *            the message
     * @param data
     *            the data
     */
    public RightSplitNotDefinedException(String message, Map<String, Object> data) {
	super(WccExceptionCodeEnum.VALIDATION_ERROR, message, data);
    }

}
