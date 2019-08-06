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
package org.wipo.connect.common.querypagination;

import java.io.Serializable;

public class Search implements Serializable {

    private static final long serialVersionUID = 4864154452735537243L;

    private String value;
    private Boolean regex;

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

    public Boolean getRegex() {
	return regex;
    }

    public void setRegex(Boolean regex) {
	this.regex = regex;
    }

}
