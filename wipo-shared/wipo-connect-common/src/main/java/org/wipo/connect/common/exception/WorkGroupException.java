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
 * The Class WorkGroupException.
 */
@JsonIgnoreProperties(ignoreUnknown = true, value = { "cause", "stackTrace", "defaultResourceBundleName" })
public class WorkGroupException extends WccException {

    private static final long serialVersionUID = 6369687185206281533L;

    @JsonCreator
    protected WorkGroupException(@JsonProperty(value = "code", required = true) WccExceptionCodeEnum code, @JsonProperty(value = "message", required = false) String message,
	    @JsonProperty(value = "data", required = false) Map<String, Object> data, @JsonProperty(value = "uuid", required = true) String uuid) {
	super(code, message, data, uuid);
    }

    public WorkGroupException(String message) {
	super(WccExceptionCodeEnum.VALIDATION_ERROR, message);
    }

    public WorkGroupException(String message, Map<String, Object> data) {
	super(WccExceptionCodeEnum.VALIDATION_ERROR, message, data);
    }

}
