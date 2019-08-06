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

package org.wipo.connect.shared.exchange.vo;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * The Class IdentifierSearchVO.
 */
public class IdentifierSearchVO implements Serializable {

    private static final long serialVersionUID = 8318463011243270723L;

    private String type;

    private String value;
    
    public IdentifierSearchVO(){}
    
    public IdentifierSearchVO(String type, String value){
	this.type = type;
	this.value = value;
    }

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

}
