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

package org.wipo.connect.shared.exchange.restvo.interestedParty;

import java.io.Serializable;

import org.wipo.connect.shared.exchange.dto.impl.IpImport;

/**
 * The Class InsertNewImportFileRestVO.
 */
public class InsertNewImportFileRestVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7068284999502982691L;
	
	/** The p import. */
	private IpImport pImport;

	/**
	 * Gets the p import.
	 *
	 * @return the p import
	 */
	public IpImport getpImport() {
		return pImport;
	}

	/**
	 * Sets the p import.
	 *
	 * @param pImport the new p import
	 */
	public void setpImport(IpImport pImport) {
		this.pImport = pImport;
	}
}
