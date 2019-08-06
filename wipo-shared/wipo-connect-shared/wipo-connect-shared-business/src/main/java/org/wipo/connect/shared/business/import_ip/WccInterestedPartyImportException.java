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
package org.wipo.connect.shared.business.import_ip;

import org.wipo.connect.enumerator.WccIpImportExceptionCodeEnum;

/**
 * The Class WccInterestedPartyImportException.
 */
public class WccInterestedPartyImportException extends Exception {

	private static final long serialVersionUID = 3205206833554172611L;
	private WccIpImportExceptionCodeEnum errorCode;

    /**
     * Instantiates a new wcc interested party import exception.
     *
     * @param message the message
     */
    public WccInterestedPartyImportException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new wcc interested party import exception.
	 *
	 * @param code the code
	 */
	public WccInterestedPartyImportException(WccIpImportExceptionCodeEnum code) {
	    super("IMPORT ERROR: " + code.getCode() + " (" + code.name() + ")");
	    this.errorCode=code;
	}

    /**
     * Instantiates a new wcc interested party import exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public WccInterestedPartyImportException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public WccIpImportExceptionCodeEnum getErrorCode() {
		return errorCode;
	}


}
