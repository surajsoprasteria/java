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

import org.wipo.connect.shared.exchange.dto.impl.ReferenceTypeFlat;


/**
 * The Class InsertOrUpdateReferenceTypeRestVO.
 */
public class InsertOrUpdateReferenceTypeRestVO implements Serializable {
	
	private static final long serialVersionUID = 8529991633189112737L;
	/** The dto. */
	
	private ReferenceTypeFlat dto;

	/**
	 * Gets the dto.
	 *
	 * @return the dto
	 */
	public ReferenceTypeFlat getDto() {
		return dto;
	}

	/**
	 * Sets the dto.
	 *
	 * @param dto the new dto
	 */
	public void setDto(ReferenceTypeFlat dto) {
		this.dto = dto;
	}


	
}
