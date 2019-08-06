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

package org.wipo.connect.shared.exchange.dto.impl;

import java.io.Serializable;

public class BIParameterFlat implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4690685605147472274L;

    /** The query code. */
    private String code;

    private String type;

    private String htmlType;

    private String description;

    private String formValue;

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getFormValue() {
	return formValue;
    }

    public void setFormValue(String formValue) {
	this.formValue = formValue;
    }

    public String getHtmlType() {
	return htmlType;
    }

    public void setHtmlType(String htmlType) {
	this.htmlType = htmlType;
    }

}
