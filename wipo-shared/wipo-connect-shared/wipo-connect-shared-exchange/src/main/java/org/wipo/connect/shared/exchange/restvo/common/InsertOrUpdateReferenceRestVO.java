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

import org.wipo.connect.shared.exchange.dto.impl.ReferenceFlat;


/**
 * The Class InsertOrUpdateReferenceRestVO.
 */
public class InsertOrUpdateReferenceRestVO implements Serializable {
	
	private static final long serialVersionUID = 8529991633189112737L;
	/** The dto. */
	
	private ReferenceFlat dto;

	/**
	 * Gets the dto.
	 *
	 * @return the dto
	 */
	public ReferenceFlat getDto() {
		return dto;
	}

	/**
	 * Sets the dto.
	 *
	 * @param dto the new dto
	 */
	public void setDto(ReferenceFlat dto) {
		this.dto = dto;
	}


	
}
