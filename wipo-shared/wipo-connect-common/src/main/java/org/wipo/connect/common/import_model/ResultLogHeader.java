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

public enum ResultLogHeader {

    STATUS(38, 23), OUTPUT_ID(39, 24);
    /** The code. */
    private final Integer code;
    private final Integer sharedCode;

    private ResultLogHeader(Integer code, Integer sharedCode) {
	this.code = code;
	this.sharedCode = sharedCode;
    }

    public Integer getCode() {
	return code;
    }

    public Integer getSharedCode() {
	return sharedCode;
    }

}
