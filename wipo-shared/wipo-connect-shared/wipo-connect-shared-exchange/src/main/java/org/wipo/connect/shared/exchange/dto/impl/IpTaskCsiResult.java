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

import org.wipo.connect.common.dto.Identifiable;



/**
 * The Class IpTaskCsiResult.
 *
 */
public class IpTaskCsiResult implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6313254608538284081L;


    private Long idIpTaskCsiResult;

    private Long fkIpTaskItem;

    /** The status code. */
    private String statusCode;

    /** The item code. */
    private String itemCode;


    @Override
    public Long getId() {
        return getIdIpTaskCsiResult();
    }


    @Override
    public void setId(Long id) {
        setIdIpTaskCsiResult(id);
    }
    
    /**
     * Gets the item code.
     *
     * @return the item code
     */
    public String getItemCode() {
        return itemCode;
    }

    /**
     * Sets the item code.
     *
     * @param itemCode the new item code
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

	/**
	 * Gets the status code.
	 *
	 * @return the status code
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * Sets the status code.
	 *
	 * @param statusCode the new status code
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}


	public Long getIdIpTaskCsiResult() {
		return idIpTaskCsiResult;
	}


	public void setIdIpTaskCsiResult(Long idIpTaskCsiResult) {
		this.idIpTaskCsiResult = idIpTaskCsiResult;
	}


	public Long getFkIpTaskItem() {
		return fkIpTaskItem;
	}


	public void setFkIpTaskItem(Long fkIpTaskItem) {
		this.fkIpTaskItem = fkIpTaskItem;
	}
}
