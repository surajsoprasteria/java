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
package org.wipo.connect.common.import_model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BaseWorkRow implements Serializable {

    private static final long serialVersionUID = 2899179534507071908L;

    @JsonIgnore
    private String errorCode;
    @JsonIgnore
    private String status;
    @JsonIgnore
    private String ids;

    public String getErrorCode() {
	return errorCode;
    }

    public void setErrorCode(String errorCode) {
	this.errorCode = errorCode;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public String getIds() {
	return ids;
    }

    public void setIds(String ids) {
	this.ids = ids;
    }

}
