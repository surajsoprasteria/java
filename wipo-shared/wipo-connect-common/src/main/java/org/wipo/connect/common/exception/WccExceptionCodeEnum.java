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

import org.apache.commons.lang3.StringUtils;

public enum WccExceptionCodeEnum {

    UNKNOW_ERROR("E000", "Unknown Error"),

    GENERIC_ERROR("E001", "Generic Error"),

    SERVICE_UNAVIABLE("E002", "Service unaviable"),

    VALIDATION_ERROR("E003", "Validation error"),

    APPLICATION_ERROR("E004", "Application exception"),

    WEBSERVICE_ERROR("E005", "Webservice error"),

    TOO_MANY_RESULTS("E006", "Too Many Results"),

    WEBSERVICE_AUTHENTICATION_ERROR("E007", "Webservice authentication error"),

    IMPORT_PROCESS_STOPPED("E008", "Import process stopped"),

    IO_EXCEPTION("E009", "Input/Output error");

    private final String code;
    private final String description;

    private WccExceptionCodeEnum(String code, String description) {
	this.code = code;
	this.description = description;
    }

    public String getCode() {
	return code;
    }

    public String getDescription() {
	return description;
    }

    public static WccExceptionCodeEnum getEnumByCode(String code) {
	for (WccExceptionCodeEnum ece : WccExceptionCodeEnum.values()) {
	    if (StringUtils.equals(ece.getCode(), code)) {
		return ece;
	    }
	}
	throw new IllegalArgumentException("Invalid WccExceptionCodeEnum code: " + code);
    }

}
