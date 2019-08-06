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

import org.wipo.connect.common.dto.Creatable;
import org.wipo.connect.common.dto.Deletable;
import org.wipo.connect.common.dto.Identifiable;
import org.wipo.connect.common.dto.Updatable;

/**
 * The Class WorkDate.
 */

public class WorkDate extends BaseDTO implements Identifiable, Updatable, Creatable, Deletable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6638790062607823103L;

    /** The id work date. */
    private Long idWorkDate;

    /** The fk work. */
    private Long fkWork;

    private String dateTypeCode;

    private Date dateValue;

    /** The exec delete. */
    private Boolean execDelete = false;

    /*
     * (non-Javadoc)
     * 
     * @see org.wipo.connect.common.dto.Identifiable#getId()
     */
    @Override
    public Long getId() {
	return getIdWorkDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.wipo.connect.common.dto.Identifiable#setId(java.lang.Long)
     */
    @Override
    public void setId(Long id) {
	setIdWorkDate(id);
    }

    @Override
    public Boolean getExecDelete() {
	return execDelete;
    }

    @Override
    public void setExecDelete(Boolean execDelete) {
	this.execDelete = execDelete;
    }

    /**
     * Gets the id work date.
     *
     * @return the id work date
     */
    public Long getIdWorkDate() {
	return idWorkDate;
    }

    /**
     * Sets the id work date.
     *
     * @param idWorkDate
     *            the new id work date
     */
    public void setIdWorkDate(Long idWorkDate) {
	this.idWorkDate = idWorkDate;
    }

    /**
     * Gets the fk work.
     *
     * @return the fk work
     */
    public Long getFkWork() {
	return fkWork;
    }

    /**
     * Sets the fk work.
     *
     * @param fkWork
     *            the new fk work
     */
    public void setFkWork(Long fkWork) {
	this.fkWork = fkWork;
    }

    public Date getDateValue() {
	return dateValue;
    }

    public void setDateValue(Date dateValue) {
	this.dateValue = dateValue;
    }

    public String getDateTypeCode() {
	return dateTypeCode;
    }

    public void setDateTypeCode(String dateTypeCode) {
	this.dateTypeCode = dateTypeCode;
    }

}
