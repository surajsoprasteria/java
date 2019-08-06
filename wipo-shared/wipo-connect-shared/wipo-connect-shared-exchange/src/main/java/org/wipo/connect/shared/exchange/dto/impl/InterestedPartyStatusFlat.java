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
 * The Class InterestedPartyStatusFlat.
 */
public class InterestedPartyStatusFlat implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -5190456236991060736L;

    /** The id interested party status. */
    private Long idInterestedPartyStatus;

    /** The value. */
    private String value;

    /** The code. */
    private String code;



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
        return getIdInterestedPartyStatus();
    }



    /**
     * Gets the id interested party status.
     *
     * @return the id interested party status
     */
    public Long getIdInterestedPartyStatus() {
        return this.idInterestedPartyStatus;
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
        setIdInterestedPartyStatus(id);
    }



    /**
     * Sets the id interested party status.
     *
     * @param idInterestedPartyStatus
     *            the new id interested party status
     */
    public void setIdInterestedPartyStatus(Long idInterestedPartyStatus) {
        this.idInterestedPartyStatus = idInterestedPartyStatus;
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

}
