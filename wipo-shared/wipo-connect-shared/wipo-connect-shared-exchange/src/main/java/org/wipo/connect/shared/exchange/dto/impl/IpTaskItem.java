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
 * The Class IpTaskItem.
 */
public class IpTaskItem implements Identifiable, Serializable {

	private static final long serialVersionUID = -5812552823093617137L;

	/** The id ip task item. */
    private Long idIpTaskItem;

    /** The fk ip task. */
    private Long fkIpTask;

    /** The fk interested party. */
    private Long fkInterestedParty;

    /** The item status. */
    private String itemStatus;

    /** The progr. */
    private Long progr;

    private IpTaskItemDetail ipTaskItemDetail;

    /** The response status. */
    private String responseStatus;

    private String itemCode;

    private String cmoCode;

    public String getCmoCode() {
		return cmoCode;
	}



	public void setCmoCode(String cmoCode) {
		this.cmoCode = cmoCode;
	}



	/**
     * Gets the fk interested party.
     *
     * @return the fk interested party
     */
    public Long getFkInterestedParty() {
        return this.fkInterestedParty;
    }



    /**
     * Gets the fk ip task.
     *
     * @return the fk ip task
     */
    public Long getFkIpTask() {
        return this.fkIpTask;
    }



    /**
     * Gets the id ip task item.
     *
     * @return the id ip task item
     */
    public Long getIdIpTaskItem() {
        return this.idIpTaskItem;
    }



    /**
     * Gets the progr.
     *
     * @return the progr
     */
    public Long getProgr() {
        return this.progr;
    }



    /**
     * Sets the fk interested party.
     *
     * @param fkInterestedParty
     *            the new fk interested party
     */
    public void setFkInterestedParty(Long fkInterestedParty) {
        this.fkInterestedParty = fkInterestedParty;
    }



    /**
     * Sets the fk ip task.
     *
     * @param fkIpTask
     *            the new fk ip task
     */
    public void setFkIpTask(Long fkIpTask) {
        this.fkIpTask = fkIpTask;
    }



    /**
     * Sets the id ip task item.
     *
     * @param idIpTaskItem
     *            the new id ip task item
     */
    public void setIdIpTaskItem(Long idIpTaskItem) {
        this.idIpTaskItem = idIpTaskItem;
    }



    /**
     * Sets the progr.
     *
     * @param progr
     *            the new progr
     */
    public void setProgr(Long progr) {
        this.progr = progr;
    }




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }



	public String getItemStatus() {
		return itemStatus;
	}



	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}



	public IpTaskItemDetail getIpTaskItemDetail() {
		return ipTaskItemDetail;
	}



	public void setIpTaskItemDetail(IpTaskItemDetail ipTaskItemDetail) {
		this.ipTaskItemDetail = ipTaskItemDetail;
	}



	public String getResponseStatus() {
		return responseStatus;
	}



	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}



	public String getItemCode() {
		return itemCode;
	}



	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}



	@Override
	public Long getId() {
		return getIdIpTaskItem();
	}



	@Override
	public void setId(Long id) {
		setIdIpTaskItem(id);
	}


}
