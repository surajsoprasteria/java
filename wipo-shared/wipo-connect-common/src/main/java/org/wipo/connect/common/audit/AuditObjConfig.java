/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */
package org.wipo.connect.common.audit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AuditObjConfig implements Serializable {

    private static final long serialVersionUID = 5528168253447178226L;

    private String name;
    private AuditEntityTypeEnum type;
    private String javaClass;
    private List<AuditFieldConfig> fields;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public AuditEntityTypeEnum getType() {
	return type;
    }

    public void setType(AuditEntityTypeEnum type) {
	this.type = type;
    }

    public String getJavaClass() {
	return javaClass;
    }

    public void setJavaClass(String javaClass) {
	this.javaClass = javaClass;
    }

    public List<AuditFieldConfig> getFields() {
	if (fields == null) {
	    fields = new ArrayList<>();
	}
	return fields;
    }

    public void setFields(List<AuditFieldConfig> fields) {
	this.fields = fields;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
