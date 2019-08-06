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
 * The Class WorkStatusFlat.
 */
public class WorkStatusFlat implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5190456236991060736L;

    /** The id work status. */
    private Long idWorkStatus;

    /** The value. */
    private String value;

    /** The code. */
    private String code;

    /** The sort order. */
    private Integer sortOrder;


    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return this.code;
    }




    @Override
    public Long getId() {
        return getIdWorkStatus();
    }



    /**
     * Gets the id work status.
     *
     * @return the id work status
     */
    public Long getIdWorkStatus() {
        return this.idWorkStatus;
    }



    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return this.value;
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




    @Override
    public void setId(Long id) {
        setIdWorkStatus(id);
    }



    /**
     * Sets the id work status.
     *
     * @param idWorkStatus
     *            the new id work status
     */
    public void setIdWorkStatus(Long idWorkStatus) {
        this.idWorkStatus = idWorkStatus;
    }



    /**
     * Sets the value.
     *
     * @param value
     *            the new value
     */
    public void setValue(String value) {
        this.value = value;
    }




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
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

}
