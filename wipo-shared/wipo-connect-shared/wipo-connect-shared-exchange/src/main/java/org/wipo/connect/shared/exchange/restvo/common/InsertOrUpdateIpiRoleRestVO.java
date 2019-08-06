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

package org.wipo.connect.shared.exchange.restvo.common;

import java.io.Serializable;

import org.wipo.connect.shared.exchange.dto.impl.IpiRoleFlat;


/**
 * The Class InsertOrUpdateIpiRoleRestVO.
 */
public class InsertOrUpdateIpiRoleRestVO implements Serializable {
	

	private static final long serialVersionUID = -8832787507794812507L;
	
	/** The dto. */
	
	private IpiRoleFlat dto;

	/**
	 * Gets the dto.
	 *
	 * @return the dto
	 */
	public IpiRoleFlat getDto() {
		return dto;
	}

	/**
	 * Sets the dto.
	 *
	 * @param dto the new dto
	 */
	public void setDto(IpiRoleFlat dto) {
		this.dto = dto;
	}


	
}
