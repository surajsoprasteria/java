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
 * The Class SharedNotAvailableException.
 */
@JsonIgnoreProperties(ignoreUnknown = true,value={"cause","stackTrace", "defaultResourceBundleName"})
public class SharedNotAvailableException extends WccException {

	private static final long serialVersionUID = -8360358032410841928L;


    @JsonCreator
    protected SharedNotAvailableException(
    		@JsonProperty(value = "code", required = true) WccExceptionCodeEnum code,
            @JsonProperty(value = "message", required = false) String message,
            @JsonProperty(value = "data", required = false) Map<String, Object> data,
            @JsonProperty(value = "uuid", required = true) String uuid) {
        super(code, message, data, uuid);
    }



    /**
     * Instantiates a new shared not available exception.
     *
     * @param code the code
     * @param message the message
     * @param cause the cause
     */
    public SharedNotAvailableException(WccExceptionCodeEnum code, String message, Throwable cause) {
		super(code, message, cause);
	}



    /**
     * Instantiates a new shared not available exception.
     *
     * @param message the message
     */
    public SharedNotAvailableException(String message) {
        super(WccExceptionCodeEnum.APPLICATION_ERROR, message);
    }


}
