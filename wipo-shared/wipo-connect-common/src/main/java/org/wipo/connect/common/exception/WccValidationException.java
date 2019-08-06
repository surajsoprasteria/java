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

import org.wipo.connect.common.customvalidation.CustomValidationError;
import org.wipo.connect.common.customvalidation.CustomValidationTypeEnum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class WccValidationException extends WccException {

    private static final long serialVersionUID = 5840591518934548442L;

    @JsonIgnore
    private List<CustomValidationError> errorList;

    private String customValidationType;

    @JsonCreator
    protected WccValidationException(@JsonProperty(value = "code", required = true) WccExceptionCodeEnum code,
	    @JsonProperty(value = "message", required = false) String message,
	    @JsonProperty(value = "data", required = false) Map<String, Object> data,
	    @JsonProperty(value = "uuid", required = true) String uuid,
	    @JsonProperty(value = "customValidationType", required = false) String customValidationType) {
	super(code, message, data, uuid);
	this.customValidationType = customValidationType;
    }

    public WccValidationException(String message) {
	super(WccExceptionCodeEnum.VALIDATION_ERROR, message);
    }

    public WccValidationException(WccExceptionCodeEnum code, String message) {
	super(code, message);
    }

    public WccValidationException(WccExceptionCodeEnum code, String message, CustomValidationTypeEnum customValidationType) {
	super(code, message);
	this.customValidationType = customValidationType.name();
    }

    public WccValidationException(List<CustomValidationError> errorList) {
	super(WccExceptionCodeEnum.VALIDATION_ERROR, "Custom Validation error");
	this.errorList = errorList;
    }

    public WccValidationException(List<CustomValidationError> errorList, CustomValidationTypeEnum customValidationType) {
	super(WccExceptionCodeEnum.VALIDATION_ERROR, "Custom Validation error");
	this.errorList = errorList;
	this.customValidationType = customValidationType.name();
    }

    public List<CustomValidationError> getErrorList() {
	if (errorList == null) {
	    errorList = new ArrayList<>();
	}
	return errorList;
    }

    public void setErrorList(List<CustomValidationError> errorList) {
	this.errorList = errorList;
    }

    public String getCustomValidationType() {
	return customValidationType;
    }

    public void setCustomValidationType(String customValidationType) {
	this.customValidationType = customValidationType;
    }

}
