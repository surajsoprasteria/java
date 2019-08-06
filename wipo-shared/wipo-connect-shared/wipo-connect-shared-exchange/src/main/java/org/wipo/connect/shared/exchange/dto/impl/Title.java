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

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.caseconverter.CaseConversion;
import org.wipo.connect.common.dto.Creatable;
import org.wipo.connect.common.dto.Deletable;
import org.wipo.connect.common.dto.Identifiable;
import org.wipo.connect.common.dto.Updatable;

/**
 * The Class Title.
 */
public class Title extends BaseDTO implements Identifiable, Updatable,
	Creatable, Deletable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 8468770140223674604L;

    /** The id title. */
    private Long idTitle;

    /** The fk type. */
    private Long fkType;

    private Long fkWork;

    /** The type code. */
    private String typeCode;

    /** The description. */
    @CaseConversion
    private String description;

    /** The exec delete. */
    private Boolean execDelete = false;

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
	return this.description;
    }

    @Override
    public Boolean getExecDelete() {
	return this.execDelete;
    }

    /**
     * Gets the fk type.
     *
     * @return the fk type
     */
    public Long getFkType() {
	return this.fkType;
    }

    @Override
    public Long getId() {
	return getIdTitle();
    }

    /**
     * Gets the id title.
     *
     * @return the id title
     */
    public Long getIdTitle() {
	return this.idTitle;
    }

    /**
     * Gets the type code.
     *
     * @return the type code
     */
    public String getTypeCode() {
	return this.typeCode;
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

    @Override
    public void setExecDelete(Boolean execDelete) {
	this.execDelete = execDelete;
    }

    /**
     * Sets the fk type.
     *
     * @param fkType
     *            the new fk type
     */
    public void setFkType(Long fkType) {
	this.fkType = fkType;
    }

    @Override
    public void setId(Long id) {
	setIdTitle(id);
    }

    /**
     * Sets the id title.
     *
     * @param idTitle
     *            the new id title
     */
    public void setIdTitle(Long idTitle) {
	this.idTitle = idTitle;
    }

    /**
     * Sets the type code.
     *
     * @param typeCode
     *            the new type code
     */
    public void setTypeCode(String typeCode) {
	this.typeCode = typeCode;
    }

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
    }

    public Long getFkWork() {
	return fkWork;
    }

    public void setFkWork(Long fkWork) {
	this.fkWork = fkWork;
    }

}
