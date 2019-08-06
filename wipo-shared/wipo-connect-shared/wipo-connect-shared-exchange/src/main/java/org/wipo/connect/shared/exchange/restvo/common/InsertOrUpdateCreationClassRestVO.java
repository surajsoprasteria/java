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

import org.wipo.connect.shared.exchange.dto.impl.CreationClassFlat;


/**
 * The Class InsertOrUpdateCreationClassVO.
 */
public class InsertOrUpdateCreationClassRestVO implements Serializable {
	
	private static final long serialVersionUID = 5078524204082423090L;
	/** The dto. */
	
	private CreationClassFlat dto;

	/**
	 * Gets the dto.
	 *
	 * @return the dto
	 */
	public CreationClassFlat getDto() {
		return dto;
	}

	/**
	 * Sets the dto.
	 *
	 * @param dto the new dto
	 */
	public void setDto(CreationClassFlat dto) {
		this.dto = dto;
	}


	
}
