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

import org.wipo.connect.shared.exchange.dto.impl.IdentifierFlat;


/**
 * The Class InsertOrUpdateLocalIdentifierRestVO.
 */
public class InsertOrUpdateIdentifierRestVO implements Serializable {
	
	private static final long serialVersionUID = -4093647245388712527L;
	/** The dto. */
	
	private IdentifierFlat dto;

	/**
	 * Gets the dto.
	 *
	 * @return the dto
	 */
	public IdentifierFlat getDto() {
		return dto;
	}

	/**
	 * Sets the dto.
	 *
	 * @param dto the new dto
	 */
	public void setDto(IdentifierFlat dto) {
		this.dto = dto;
	}


	
}
