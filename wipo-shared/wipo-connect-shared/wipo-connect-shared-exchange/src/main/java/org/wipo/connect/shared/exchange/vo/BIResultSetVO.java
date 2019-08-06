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

package org.wipo.connect.shared.exchange.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.wipo.connect.shared.exchange.dto.impl.BIResultSet;

public class BIResultSetVO implements Serializable {

    private static final long serialVersionUID = 1653222588998472486L;

    private Map<String, Object> columns;
    private List<Map<String, Object>> data;
    private Integer draw;
    private Integer recordsFiltered;
    private Integer recordsTotal;

    public BIResultSetVO() {
	super();
    }

    public BIResultSetVO(boolean empty) {
	this.columns = new LinkedHashMap<>();
	this.data = new ArrayList<>();
	this.draw = 0;
	this.recordsTotal = 0;
	this.recordsFiltered = 0;
    }

    public BIResultSetVO(BIResultSet result, Integer draw, Integer recordsTotal) {
	this.columns = new LinkedHashMap<>();
	this.data = result.getData();
	this.draw = draw;
	this.recordsTotal = recordsTotal;
	this.recordsFiltered = recordsTotal;

	for (Entry<String, Object> col : result.getColumns().entrySet()) {
	    String newKey = StringUtils.replace(col.getKey(), ".", "&#46;");
	    columns.put(newKey, col.getValue());
	    data.forEach(d -> d.put(newKey, d.remove(col.getKey())));
	}
    }

    public Map<String, Object> asMap() {
	Map<String, Object> map = new HashMap<>();
	map.put("data", data);
	map.put("columnsSet", columns);
	map.put("columns", getColumnsDataTable());
	map.put("draw", draw);
	map.put("recordsFiltered", recordsFiltered);
	map.put("recordsTotal", recordsTotal);
	return map;
    }

    public LinkedList<Map<String, Object>> getColumnsDataTable() {
	LinkedList<Map<String, Object>> dtCols = new LinkedList<>();
	for (String col : columns.keySet()) {
	    Map<String, Object> dtCol = new LinkedHashMap<String, Object>();
	    dtCol.put("title", col);
	    dtCol.put("name", col);
	    dtCol.put("data", col);
	    dtCol.put("searchable", false);
	    dtCols.add(dtCol);
	}

	if (dtCols.isEmpty()) {
	    Map<String, Object> dtCol = new LinkedHashMap<String, Object>();
	    dtCol.put("title", "&nbsp;");
	    dtCol.put("orderable", false);
	    dtCol.put("searchable", false);
	    dtCols.add(dtCol);
	}
	return dtCols;

    }

    public Map<String, Object> getColumns() {
	if (columns == null) {
	    columns = new LinkedHashMap<>();
	}
	return columns;
    }

    public void setColumns(Map<String, Object> columns) {
	this.columns = columns;
    }

    public List<Map<String, Object>> getData() {
	return data;
    }

    public void setData(List<Map<String, Object>> data) {
	this.data = data;
    }

    public Integer getDraw() {
	return draw;
    }

    public void setDraw(Integer draw) {
	this.draw = draw;
    }

    public Integer getRecordsFiltered() {
	return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
	this.recordsFiltered = recordsFiltered;
    }

    public Integer getRecordsTotal() {
	return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
	this.recordsTotal = recordsTotal;
    }

}
