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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BIResultSet implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4690685605147472274L;

    private Map<String, Object> columns;
    private List<Map<String, Object>> data;

    public Map<String, Object> getColumns() {
	if (null == columns) {
	    columns = new LinkedHashMap<String, Object>();
	}
	return columns;
    }

    public void setColumns(Map<String, Object> columns) {
	this.columns = columns;
    }

    public List<Map<String, Object>> getData() {
	if (null == data) {
	    data = new ArrayList<Map<String, Object>>();
	}
	return data;
    }

    public void setData(List<Map<String, Object>> data) {
	this.data = data;
    }

}
