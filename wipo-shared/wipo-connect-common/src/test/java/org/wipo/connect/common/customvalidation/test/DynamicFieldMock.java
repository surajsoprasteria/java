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
package org.wipo.connect.common.customvalidation.test;

import org.wipo.connect.common.customvalidation.IDynamicFieldValidation;

public class DynamicFieldMock implements IDynamicFieldValidation {

    private static final long serialVersionUID = 1332601786964206920L;

    private String code;
    private String typeCode;
    private Boolean mandatory;
    private Integer maxLength;
    private Object value;

    public DynamicFieldMock(String code,
	    String typeCode,
	    Boolean mandatory,
	    Integer maxLength,
	    Object value) {
	super();
	this.code = code;
	this.typeCode = typeCode;
	this.mandatory = mandatory;
	this.maxLength = maxLength;
	this.value = value;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public void setMandatory(Boolean mandatory) {
	this.mandatory = mandatory;
    }

    public void setMaxLength(Integer maxLength) {
	this.maxLength = maxLength;
    }

    public void setValue(Object value) {
	this.value = value;
    }

    public void setTypeCode(String typeCode) {
	this.typeCode = typeCode;
    }

    @Override
    public String getCode() {
	return code;
    }

    @Override
    public Boolean getMandatory() {
	return mandatory;
    }

    @Override
    public Integer getMaxLength() {
	return maxLength;
    }

    @Override
    public Object getValue() {
	return value;
    }

    @Override
    public String getTypeCode() {
	return typeCode;
    }

}
