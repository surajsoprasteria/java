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

import org.wipo.connect.shared.exchange.dto.impl.RoleFlat;


/**
 * The Class InsertOrUpdateRightSplitRestVO.
 */
public class InsertOrUpdateRoleRestVO implements Serializable {
	

	private static final long serialVersionUID = -8832787507794812507L;
	
	/** The dto. */
	
	private RoleFlat dto;

	/**
	 * Gets the dto.
	 *
	 * @return the dto
	 */
	public RoleFlat getDto() {
		return dto;
	}

	/**
	 * Sets the dto.
	 *
	 * @param dto the new dto
	 */
	public void setDto(RoleFlat dto) {
		this.dto = dto;
	}


	
}
