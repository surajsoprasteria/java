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

public class Column implements Serializable {

    private static final long serialVersionUID = -4699074598769372570L;

    private String data;
    private String name;
    private Boolean searchable;
    private Boolean orderable;
    private Search search;

    public String getData() {
	return data;
    }

    public void setData(String data) {
	this.data = data;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Boolean getSearchable() {
	return searchable;
    }

    public void setSearchable(Boolean searchable) {
	this.searchable = searchable;
    }

    public Boolean getOrderable() {
	return orderable;
    }

    public void setOrderable(Boolean orderable) {
	this.orderable = orderable;
    }

    public Search getSearch() {
	return search;
    }

    public void setSearch(Search search) {
	this.search = search;
    }

}
