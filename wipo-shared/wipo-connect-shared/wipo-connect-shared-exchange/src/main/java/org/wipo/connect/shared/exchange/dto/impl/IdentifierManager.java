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

public class IdentifierManager implements Identifiable, Serializable {

	private static final long serialVersionUID = 2305115976593283720L;
	private Long idIdentifierManager;
	private String refTable;
	private Long lastVal;

	@Override
	public Long getId() {
		return getIdIdentifierManager();
	}

	@Override
	public void setId(Long id) {
		setIdIdentifierManager(id);
	}

	public Long getIdIdentifierManager() {
		return idIdentifierManager;
	}

	public void setIdIdentifierManager(Long idIdentifierManager) {
		this.idIdentifierManager = idIdentifierManager;
	}

	public Long getLastVal() {
		return lastVal;
	}

	public void setLastVal(Long lastVal) {
		this.lastVal = lastVal;
	}

	public String getRefTable() {
		return refTable;
	}

	public void setRefTable(String refTable) {
		this.refTable = refTable;
	}

}
