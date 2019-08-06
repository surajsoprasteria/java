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

package org.wipo.connect.common.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class TerritoryValidationException.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TerritoryValidationException extends WccException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 584952438918278044L;

	/**
	 * Instantiates a new territory validation exception.
	 *
	 * @param message the message
	 */
	public TerritoryValidationException(String message) {
		super(WccExceptionCodeEnum.APPLICATION_ERROR, message);
	}

}
