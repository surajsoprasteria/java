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
package org.wipo.connect.scriptengine.test.model;

public class ResultDTO {
    private String message;
    private Integer code;

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public Integer getCode() {
	return code;
    }

    public void setCode(Integer code) {
	this.code = code;
    }
}