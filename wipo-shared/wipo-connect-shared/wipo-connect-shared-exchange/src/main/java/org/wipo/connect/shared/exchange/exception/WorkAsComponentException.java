/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */
package org.wipo.connect.shared.exchange.exception;

import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class WorkAsComponentException.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkAsComponentException extends WccException{

	public WorkAsComponentException(
			String message) {
		super(WccExceptionCodeEnum.APPLICATION_ERROR, message);
	}

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8488004724896564951L;




}
