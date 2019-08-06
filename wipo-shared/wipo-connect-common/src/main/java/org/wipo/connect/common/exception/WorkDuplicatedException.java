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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WorkDuplicatedException extends WccException {

    private static final long serialVersionUID = 5840591518934548442L;

    private List<String> errorList;

    @JsonCreator
    protected WorkDuplicatedException(@JsonProperty(value = "code", required = true) WccExceptionCodeEnum code,
	    @JsonProperty(value = "message", required = false) String message,
	    @JsonProperty(value = "data", required = false) Map<String, Object> data,
	    @JsonProperty(value = "uuid", required = true) String uuid,
	    @JsonProperty(value = "errorList", required = false) List<String> errorList) {
	super(code, message, data, uuid);
	this.errorList = errorList;
    }

    public WorkDuplicatedException(String message) {
	super(WccExceptionCodeEnum.VALIDATION_ERROR, message);
    }

    public WorkDuplicatedException(WccExceptionCodeEnum code, String message) {
	super(code, message);
    }

    public WorkDuplicatedException(List<String> errorList) {
	super(WccExceptionCodeEnum.VALIDATION_ERROR, "Work duplicated error");
	this.errorList = errorList;
    }

    public List<String> getErrorList() {
	if (errorList == null) {
	    errorList = new ArrayList<>();
	}
	return errorList;
    }

    public void setErrorList(List<String> errorList) {
	this.errorList = errorList;
    }

}
