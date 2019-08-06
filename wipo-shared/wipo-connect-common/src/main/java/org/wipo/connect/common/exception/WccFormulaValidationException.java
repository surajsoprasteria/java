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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class .
 *
 * @author fumagalli
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WccFormulaValidationException extends WccException {

    private static final long serialVersionUID = -8793068027541895940L;

    /**
     * Instantiates a new wcc formula validation exception.
     *
     * @param code
     *            the code
     * @param message
     *            the message
     */
    public WccFormulaValidationException(WccExceptionCodeEnum code, String message) {
	super(code, message);
    }

    @JsonCreator
    protected WccFormulaValidationException(@JsonProperty(value = "code", required = true) WccExceptionCodeEnum code, @JsonProperty(value = "message", required = false) String message,
	    @JsonProperty(value = "data", required = false) Map<String, Object> data, @JsonProperty(value = "uuid", required = true) String uuid) {
	super(code, message, data, uuid);
    }

    public WccFormulaValidationException(String message, Throwable cause) {
	super(WccExceptionCodeEnum.VALIDATION_ERROR, message, cause, new HashMap<String, Object>());
    }

}
