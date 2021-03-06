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

package org.wipo.connect.shared.exchange.enumerator;

public enum QueryParameterTypeEnum {

    STRING("text"),

    INTEGER(""),

    DATE("date"),

    BOOLEAN("checkbox"),

    DECIMAL("");

    private String htmlType;

    private QueryParameterTypeEnum(String type) {
	this.htmlType = type;
    }

    public String getHtmlType() {
	return htmlType;
    }

}
