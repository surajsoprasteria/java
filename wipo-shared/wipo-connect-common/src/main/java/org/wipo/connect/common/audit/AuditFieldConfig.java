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

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AuditFieldConfig implements Serializable {

    private static final long serialVersionUID = -6685224997801483564L;

    private String fieldName;
    private Boolean enableAudit;
    private Boolean primaryKey;

    public String getFieldName() {
	return fieldName;
    }

    public void setFieldName(String fieldName) {
	this.fieldName = fieldName;
    }

    public Boolean getEnableAudit() {
	return enableAudit;
    }

    public void setEnableAudit(Boolean enableAudit) {
	this.enableAudit = enableAudit;
    }

    public Boolean getPrimaryKey() {
	return primaryKey;
    }

    public void setPrimaryKey(Boolean primaryKey) {
	this.primaryKey = primaryKey;
    }

    @Override
    public String toString() {
	return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
