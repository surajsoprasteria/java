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

package org.wipo.connect.work.response;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * The Class DerivedViewNameList.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DerivedViewNameList", propOrder = {
	"roleCode",
	"name",
	"refIndex",
	"creatorRefIndex",
	"creatorRefMainId",
	"sourceTypeCode",
	"derivedViewNameShareList"
})
public class DerivedViewNameList {

    @XmlElement(required = true)
    protected String roleCode;
    @XmlElement(required = true)
    protected NameType name;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long refIndex;
    @XmlElement(required = true, type = Long.class, nillable = true)
    protected Long creatorRefIndex;
    @XmlElement(required = false)
    protected String creatorRefMainId;
    @XmlElement(required = true, nillable = true)
    protected String sourceTypeCode;
    @XmlElement(required = true)
    protected List<DerivedViewNameShare> derivedViewNameShareList;

    /**
     * Gets the role code.
     *
     * @return the role code
     */
    public String getRoleCode() {
	return roleCode;
    }

    /**
     * Sets the role code.
     *
     * @param value
     *            the new role code
     */
    public void setRoleCode(String value) {
	this.roleCode = value;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public NameType getName() {
	return name;
    }

    /**
     * Sets the name.
     *
     * @param value
     *            the new name
     */
    public void setName(NameType value) {
	this.name = value;
    }

    /**
     * Gets the ref index.
     *
     * @return the ref index
     */
    public Long getRefIndex() {
	return refIndex;
    }

    /**
     * Sets the ref index.
     *
     * @param value
     *            the new ref index
     */
    public void setRefIndex(Long value) {
	this.refIndex = value;
    }

    /**
     * Gets the creator ref index.
     *
     * @return the creator ref index
     */
    public Long getCreatorRefIndex() {
	return creatorRefIndex;
    }

    /**
     * Sets the creator ref index.
     *
     * @param value
     *            the new creator ref index
     */
    public void setCreatorRefIndex(Long value) {
	this.creatorRefIndex = value;
    }

    public String getCreatorRefMainId() {
	return creatorRefMainId;
    }

    public void setCreatorRefMainId(String creatorRefMainId) {
	this.creatorRefMainId = creatorRefMainId;
    }

    /**
     * Gets the source type code.
     *
     * @return the source type code
     */
    public String getSourceTypeCode() {
	return sourceTypeCode;
    }

    /**
     * Sets the source type code.
     *
     * @param value
     *            the new source type code
     */
    public void setSourceTypeCode(String value) {
	this.sourceTypeCode = value;
    }

    /**
     * Gets the derived view name share list.
     *
     * @return the derived view name share list
     */
    public List<DerivedViewNameShare> getDerivedViewNameShareList() {
	if (derivedViewNameShareList == null) {
	    derivedViewNameShareList = new ArrayList<>();
	}
	return this.derivedViewNameShareList;
    }

}
