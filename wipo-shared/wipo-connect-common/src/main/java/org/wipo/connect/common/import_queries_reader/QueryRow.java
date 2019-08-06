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
package org.wipo.connect.common.import_queries_reader;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

public class QueryRow implements Serializable {

    private static final long serialVersionUID = 2248853570316901466L;

    private String queryName;
    private String querySql;
    private String queryParameter;

    public String getQueryName() {
	return queryName;
    }

    public void setQueryName(String queryName) {
	this.queryName = queryName;
    }

    public String getQuerySql() {
	return querySql;
    }

    public void setQuerySql(String querySql) {
	this.querySql = querySql;
    }

    public String getQueryParameter() {
	return queryParameter;
    }

    public void setQueryParameter(String queryParameter) {
	this.queryParameter = queryParameter;
    }

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
    }

}
