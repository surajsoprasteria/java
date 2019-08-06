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
import java.util.HashMap;
import java.util.Map;

import org.wipo.connect.common.querypagination.PageInfo;

public class BISearch implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4690685605147472274L;

    /** The query code. */
    private String queryCode;

    private Map<String, BIParameterFlat> queryParameterObject;

    private PageInfo pageInfo;

    private Boolean getColumnsOnly;

    public String getQueryCode() {
	return queryCode;
    }

    public void setQueryCode(String queryCode) {
	this.queryCode = queryCode;
    }

    public Map<String, BIParameterFlat> getQueryParameterObject() {
	if (null == queryParameterObject) {
	    queryParameterObject = new HashMap<String, BIParameterFlat>();
	}
	return queryParameterObject;
    }

    public void setQueryParameterObject(Map<String, BIParameterFlat> queryParameterObject) {
	this.queryParameterObject = queryParameterObject;
    }

    public PageInfo getPageInfo() {
	return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
	this.pageInfo = pageInfo;
    }

    public Boolean getGetColumnsOnly() {
	return getColumnsOnly;
    }

    public void setGetColumnsOnly(Boolean getColumnsOnly) {
	this.getColumnsOnly = getColumnsOnly;
    }

}
