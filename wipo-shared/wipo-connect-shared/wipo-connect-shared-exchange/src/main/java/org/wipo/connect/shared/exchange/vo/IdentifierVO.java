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



package org.wipo.connect.shared.exchange.vo;



import java.io.Serializable;

import org.wipo.connect.shared.exchange.dto.impl.IdentifierFlat;



/**
 * The Class IdentifierVO.
 */
public class IdentifierVO implements Serializable {
	
    
	private static final long serialVersionUID = 1966677076601453795L;

	private IdentifierFlat identifier;

    private Boolean isToCheckCode;

	/**
	 * Gets the identifier.
	 *
	 * @return the identifier
	 */
	public IdentifierFlat getIdentifier() {
		return identifier;
	}

	/**
	 * Sets the identifier.
	 *
	 * @param identifier the new identifier
	 */
	public void setIdentifier(IdentifierFlat identifier) {
		this.identifier = identifier;
	}

	/**
	 * Gets the checks if is to check code.
	 *
	 * @return the checks if is to check code
	 */
	public Boolean getIsToCheckCode() {
		return isToCheckCode;
	}

	/**
	 * Sets the checks if is to check code.
	 *
	 * @param isToCheckCode the new checks if is to check code
	 */
	public void setIsToCheckCode(Boolean isToCheckCode) {
		this.isToCheckCode = isToCheckCode;
	}

	
}
