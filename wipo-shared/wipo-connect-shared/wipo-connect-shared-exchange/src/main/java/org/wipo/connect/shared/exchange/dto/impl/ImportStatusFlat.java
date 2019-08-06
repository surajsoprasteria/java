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

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Identifiable;



/**
 * The Class ImportStatusFlat.
 */
public class ImportStatusFlat implements Identifiable, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4642406959162469356L;

    /** The id import status. */
    private Long idImportStatus;

    /** The value. */
    private String value;

    /** The code. */
    private String code;

    /** The sort order. */
    private Integer sortOrder;



    /**
     * Gets the id import status.
     *
     * @return the id import status
     */
    public Long getIdImportStatus() {
        return idImportStatus;
    }



    /**
     * Sets the id import status.
     *
     * @param idImportStatus the new id import status
     */
    public void setIdImportStatus(Long idImportStatus) {
        this.idImportStatus = idImportStatus;
    }



    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }



    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(String value) {
        this.value = value;
    }


    /**
     * Gets the sort order.
     *
     * @return the sort order
     */
    public Integer getSortOrder() {
        return sortOrder;
    }



    /**
     * Sets the sort order.
     *
     * @param sortOrder the new sort order
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }




    @Override
    public Long getId() {
        return getIdImportStatus();
    }




    @Override
    public void setId(Long id) {
        setIdImportStatus(id);
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
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
