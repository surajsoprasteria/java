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

import org.wipo.connect.shared.exchange.dto.impl.RightTypeFlat;


/**
 * The Class InsertOrUpdateRightTypeRestVO.
 */
public class InsertOrUpdateRightTypeRestVO implements Serializable {
	

	private static final long serialVersionUID = -8832787507794812507L;
	
	/** The dto. */
	
	private RightTypeFlat dto;

	/**
	 * Gets the dto.
	 *
	 * @return the dto
	 */
	public RightTypeFlat getDto() {
		return dto;
	}

	/**
	 * Sets the dto.
	 *
	 * @param dto the new dto
	 */
	public void setDto(RightTypeFlat dto) {
		this.dto = dto;
	}


	
}
