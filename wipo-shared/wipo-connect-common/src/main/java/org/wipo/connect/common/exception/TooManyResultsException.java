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
public class TooManyResultsException extends WccException {

	private static final long serialVersionUID = 8690289304666019511L;

	private static final String NUM_RESULTS = "TooManyResultsException_NUM_RESULTS";
	private static final String MAX_RESULTS = "TooManyResultsException_MAX_RESULTS";



	@JsonCreator
    protected TooManyResultsException(
    		@JsonProperty(value = "code", required = true) WccExceptionCodeEnum code,
            @JsonProperty(value = "message", required = false) String message,
            @JsonProperty(value = "data", required = false) Map<String, Object> data,
            @JsonProperty(value = "uuid", required = true) String uuid) {
        super(code, message, data, uuid);
    }




    /**
     * Instantiates a new too many results exception.
     *
     * @param message the message
     */
    public TooManyResultsException(String message, Integer numResults, Integer maxResults) {
        super(WccExceptionCodeEnum.APPLICATION_ERROR, message);
        super.getData().put(NUM_RESULTS, numResults);
        super.getData().put(MAX_RESULTS, maxResults);
    }

    public Integer getMaxResults(){
    	return (Integer) super.getData().get(MAX_RESULTS);
    }

    public Integer getNumResults(){
    	return (Integer) super.getData().get(NUM_RESULTS);
    }

}
