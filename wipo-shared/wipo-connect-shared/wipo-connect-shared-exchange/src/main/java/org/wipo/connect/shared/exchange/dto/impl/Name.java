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

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.caseconverter.CaseConversion;
import org.wipo.connect.common.customvalidation.CustomValidatedField;
import org.wipo.connect.common.dto.Creatable;
import org.wipo.connect.common.dto.Deletable;
import org.wipo.connect.common.dto.Identifiable;
import org.wipo.connect.common.dto.Updatable;

/**
 * The Class Name.
 *
 * @author minervini
 */
@SuppressWarnings({ "squid:S1700" })
public class Name extends BaseDTO implements Identifiable, Creatable, Updatable, Deletable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7752494458088135440L;

    /** The id name. */
    private Long idName;

    /** The name. */
    @CustomValidatedField(fieldCode = "LAST_NAME")
    @CaseConversion
    private String name;

    /** The first name. */
    @CustomValidatedField(fieldCode = "FIRST_NAME")
    @CaseConversion
    private String firstName;

    /** The name type. */
    @CustomValidatedField(fieldCode = "NAME_TYPE")
    private String nameType;

    /** The creation timestamp. */
    private Date creationTimestamp;

    /** The amendment timestamp. */
    private Date amendmentTimestamp;

    /** The exec delete. */
    private Boolean execDelete = false;

    private String mainId;

    /**
     * Gets the amendment timestamp.
     *
     * @return the amendment timestamp
     */
    public Date getAmendmentTimestamp() {
	return this.amendmentTimestamp;
    }

    /**
     * Gets the creation timestamp.
     *
     * @return the creation timestamp
     */
    public Date getCreationTimestamp() {
	return this.creationTimestamp;
    }

    @Override
    public Boolean getExecDelete() {
	return this.execDelete;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
	return this.firstName;
    }

    /**
     * Gets the full name.
     *
     * @return the full name
     */
    public String getFullName() {
	return StringUtils.removeEnd(StringUtils.defaultString(this.name) + ", " + StringUtils.trimToEmpty(this.firstName), ", ");
    }

    @Override
    public Long getId() {
	return getIdName();
    }

    /**
     * Gets the id name.
     *
     * @return the id name
     */
    public Long getIdName() {
	return this.idName;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
	return this.name;
    }

    /**
     * Gets the name type.
     *
     * @return the name type
     */
    public String getNameType() {
	return this.nameType;
    }

    /**
     * Sets the amendment timestamp.
     *
     * @param amendmentTimestamp
     *            the new amendment timestamp
     */
    public void setAmendmentTimestamp(Date amendmentTimestamp) {
	this.amendmentTimestamp = amendmentTimestamp;
    }

    /**
     * Sets the creation timestamp.
     *
     * @param creationTimestamp
     *            the new creation timestamp
     */
    public void setCreationTimestamp(Date creationTimestamp) {
	this.creationTimestamp = creationTimestamp;
    }

    @Override
    public void setExecDelete(Boolean execDelete) {
	this.execDelete = execDelete;
    }

    /**
     * Sets the first name.
     *
     * @param firstName
     *            the new first name
     */
    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    @Override
    public void setId(Long id) {
	setIdName(id);

    }

    /**
     * Sets the id name.
     *
     * @param idName
     *            the new id name
     */
    public void setIdName(Long idName) {
	this.idName = idName;
    }

    /**
     * Sets the name.
     *
     * @param name
     *            the new name
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * Sets the name type.
     *
     * @param nameType
     *            the new name type
     */
    public void setNameType(String nameType) {
	this.nameType = nameType;
    }

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
    }

    public String getMainId() {
	return mainId;
    }

    public void setMainId(String mainId) {
	this.mainId = mainId;
    }

}
