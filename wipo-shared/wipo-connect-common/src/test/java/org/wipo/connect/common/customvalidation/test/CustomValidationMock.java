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

import java.math.BigDecimal;

import org.wipo.connect.common.customvalidation.ICustomValidation;

public class CustomValidationMock implements ICustomValidation {

    private static final long serialVersionUID = -7499193553811313216L;

    private Boolean forced;
    private String code;
    private Boolean international;
    private Boolean mandatory;
    private Integer maxLength;
    private BigDecimal minValue;
    private BigDecimal maxValue;
    private String regularExpression;
    private String type;
    private Long maxFileSize;
    private String possibleValues;

    @Override
    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    @Override
    public Boolean getInternational() {
	return international;
    }

    public void setInternational(Boolean international) {
	this.international = international;
    }

    @Override
    public Boolean getMandatory() {
	return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
	this.mandatory = mandatory;
    }

    @Override
    public Integer getMaxLength() {
	return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
	this.maxLength = maxLength;
    }

    @Override
    public BigDecimal getMinValue() {
	return minValue;
    }

    public void setMinValue(BigDecimal minValue) {
	this.minValue = minValue;
    }

    @Override
    public BigDecimal getMaxValue() {
	return maxValue;
    }

    public void setMaxValue(BigDecimal maxValue) {
	this.maxValue = maxValue;
    }

    @Override
    public String getRegularExpression() {
	return regularExpression;
    }

    public void setRegularExpression(String regularExpression) {
	this.regularExpression = regularExpression;
    }

    @Override
    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    @Override
    public Boolean getForced() {
	return forced;
    }

    public void setForced(Boolean forced) {
	this.forced = forced;
    }

    @Override
    public Long getMaxFileSize() {
	return maxFileSize;
    }

    public void setMaxFileSize(Long maxFileSize) {
	this.maxFileSize = maxFileSize;
    }

    @Override
    public String getPossibleValues() {
	return possibleValues;
    }

    public void setPossibleValues(String possibleValues) {
	this.possibleValues = possibleValues;
    }

}
