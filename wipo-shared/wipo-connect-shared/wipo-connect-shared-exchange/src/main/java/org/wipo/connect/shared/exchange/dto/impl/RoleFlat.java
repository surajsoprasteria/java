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
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Identifiable;

/**
 * The Class RoleFlat.
 */
public class RoleFlat implements Serializable, Identifiable {

    private static final long serialVersionUID = 946177294180357628L;

    /** The id cwr role. */
    private Long idRole;

    /** The code. */
    private String code;

    /** The fkName. */
    private Long fkName;

    /** The name. */
    private String name;

    /** The fkDescription. */
    private Long fkDescription;

    /** The description. */
    private String description;

    /** The examples. */
    private String examples;

    private Long fkIpiRole;

    private List<Long> fkCcList;

    private List<CreationClassFlat> creationClassList;

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
	return this.description;
    }

    /**
     * Gets the examples.
     *
     * @return the examples
     */
    public String getExamples() {
	return this.examples;
    }

    @Override
    public Long getId() {
	return getIdRole();
    }

    /**
     * Gets the id cwr role.
     *
     * @return the id cwr role
     */
    public Long getIdRole() {
	return this.idRole;
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
     * Sets the description.
     *
     * @param description
     *            the new description
     */
    public void setDescription(String description) {
	this.description = description;
    }

    /**
     * Sets the examples.
     *
     * @param examples
     *            the new examples
     */
    public void setExamples(String examples) {
	this.examples = examples;
    }

    @Override
    public void setId(Long id) {
	setIdRole(id);
    }

    /**
     * Sets the id cwr role.
     *
     * @param idRole
     *            the new id cwr role
     */
    public void setIdRole(Long idRole) {
	this.idRole = idRole;
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

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
	return code;
    }

    /**
     * Sets the code.
     *
     * @param code
     *            the new code
     */
    public void setCode(String code) {
	this.code = code;
    }

    public Long getFkName() {
	return fkName;
    }

    public void setFkName(Long fkName) {
	this.fkName = fkName;
    }

    public Long getFkDescription() {
	return fkDescription;
    }

    public void setFkDescription(Long fkDescription) {
	this.fkDescription = fkDescription;
    }

    public Long getFkIpiRole() {
	return fkIpiRole;
    }

    public void setFkIpiRole(Long fkIpiRole) {
	this.fkIpiRole = fkIpiRole;
    }

    public List<Long> getFkCcList() {
	return fkCcList;
    }

    public void setFkCcList(List<Long> fkCcList) {
	this.fkCcList = fkCcList;
    }

    public List<CreationClassFlat> getCreationClassList() {
	if (null == creationClassList) {
	    creationClassList = new ArrayList<>();
	}
	return creationClassList;
    }

    public void setCreationClassList(List<CreationClassFlat> creationClassList) {
	this.creationClassList = creationClassList;
    }

}
