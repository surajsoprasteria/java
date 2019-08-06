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

public enum WorkResultLogHeader {

    STATUS(25, 24), OUTPUT_ID(26, 25);

    private final Integer code;
    private final Integer sharedCode;

    private WorkResultLogHeader(Integer code, Integer sharedCode) {
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
