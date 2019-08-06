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
 * The Class IpTaskElaborationResult.
 */
public class IpTaskElaborationResult implements Serializable, Identifiable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -740078852712252955L;

	/** The id ip task item. */
	private Long idIpTaskItem;

	private Long fkInterestedParty;

	/** The item code. */
	private String itemCode;

	/** The task status. */
	private String itemStatus;

	/** The ip response status. */
	private String responseStatus;

	private String progr;

	public Long getIdIpTaskItem() {
		return idIpTaskItem;
	}

	public void setIdIpTaskItem(Long idIpTaskItem) {
		this.idIpTaskItem = idIpTaskItem;
	}

	public Long getFkInterestedParty() {
		return fkInterestedParty;
	}

	public void setFkInterestedParty(Long fkInterestedParty) {
		this.fkInterestedParty = fkInterestedParty;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public String getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

	public String getProgr() {
		return progr;
	}

	public void setProgr(String progr) {
		this.progr = progr;
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
