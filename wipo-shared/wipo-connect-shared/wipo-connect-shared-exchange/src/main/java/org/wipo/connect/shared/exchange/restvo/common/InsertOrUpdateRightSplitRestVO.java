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

import org.wipo.connect.shared.exchange.dto.impl.RightSplit;


/**
 * The Class InsertOrUpdateRightSplitRestVO.
 */
public class InsertOrUpdateRightSplitRestVO implements Serializable {
	

	private static final long serialVersionUID = 2750044991916626695L;
	/** The dto. */
	
	private RightSplit dto;

	/**
	 * Gets the dto.
	 *
	 * @return the dto
	 */
	public RightSplit getDto() {
		return dto;
	}

	/**
	 * Sets the dto.
	 *
	 * @param dto the new dto
	 */
	public void setDto(RightSplit dto) {
		this.dto = dto;
	}


	
}
