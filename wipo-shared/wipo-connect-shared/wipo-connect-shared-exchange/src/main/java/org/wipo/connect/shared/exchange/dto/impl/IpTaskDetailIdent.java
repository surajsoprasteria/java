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
package org.wipo.connect.shared.exchange.dto.impl;

import java.io.Serializable;

import org.wipo.connect.common.dto.Identifiable;

/**
 * The Class IpTaskDetailIdent.
 */
public class IpTaskDetailIdent implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 132344793461194339L;

    private Long idIpTaskDetailIdent;

    private Long fkIpTaskItemDetail;

    /** The code. */
    private String code;

    /** The value. */
    private String value;


    @Override
    public Long getId() {
    	return getIdIpTaskDetailIdent();
    }


    @Override
    public void setId(Long id) {
    	setIdIpTaskDetailIdent(id);
    }


    public Long getIdIpTaskDetailIdent() {
		return idIpTaskDetailIdent;
	}


	public void setIdIpTaskDetailIdent(Long idIpTaskDetailIdent) {
		this.idIpTaskDetailIdent = idIpTaskDetailIdent;
	}


	public Long getFkIpTaskItemDetail() {
		return fkIpTaskItemDetail;
	}


	public void setFkIpTaskItemDetail(Long fkIpTaskItemDetail) {
		this.fkIpTaskItemDetail = fkIpTaskItemDetail;
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
}
