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

import org.wipo.connect.shared.exchange.dto.impl.Cmo;


/**
 * The Class InsertOrUpdateLocalIdentifierRestVO.
 */
public class InsertOrUpdateCmoRestVO implements Serializable {
	
	private static final long serialVersionUID = 5078524204082423090L;
	/** The dto. */
	
	private Cmo dto;

	/**
	 * Gets the dto.
	 *
	 * @return the dto
	 */
	public Cmo getDto() {
		return dto;
	}

	/**
	 * Sets the dto.
	 *
	 * @param dto the new dto
	 */
	public void setDto(Cmo cmo) {
		this.dto = cmo;
	}


	
}
