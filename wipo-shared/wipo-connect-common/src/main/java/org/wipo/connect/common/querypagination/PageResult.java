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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageResult<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 4837190576494285244L;

    private List<T> data;
    private Integer draw;
    private Integer recordsFiltered;
    private Integer recordsTotal;

    public PageResult() {
	super();
    }

    public PageResult(List<T> data, Integer draw, Integer recordsFiltered, Integer recordsTotal) {
	super();
	this.data = data;
	this.draw = draw;
	this.recordsFiltered = recordsFiltered;
	this.recordsTotal = recordsTotal;
    }

    public PageResult(List<T> data, Integer draw, Integer recordsTotal) {
	this(data, draw, recordsTotal, recordsTotal);
    }

    public List<T> getData() {
	if (data == null) {
	    data = new ArrayList<>();
	}
	return data;
    }

    public void setData(List<T> data) {
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

    public Map<String, Object> asMap() {
	Map<String, Object> map = new HashMap<>();
	map.put("data", data);
	map.put("draw", draw);
	map.put("recordsFiltered", recordsFiltered);
	map.put("recordsTotal", recordsTotal);
	return map;
    }

}
