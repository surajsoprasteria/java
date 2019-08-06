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
package org.wipo.connect.shared.business.import_work;

import org.wipo.connect.enumerator.WccWorkImportExceptionCodeEnum;

/**
 * The Class WccWorkImportException.
 */
public class WccWorkImportException extends Exception {

	private static final long serialVersionUID = 3205206833554172611L;
	private WccWorkImportExceptionCodeEnum errorCode;

    /**
     * Instantiates a new wcc interested party import exception.
     *
     * @param message the message
     */
    public WccWorkImportException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new wcc interested party import exception.
	 *
	 * @param code the code
	 */
	public WccWorkImportException(WccWorkImportExceptionCodeEnum code) {
	    	super("IMPORT ERROR: " + code.getCode() + " (" + code.name() + ")");
		this.errorCode=code;
	}

    /**
     * Instantiates a new wcc interested party import exception.
     *
     * @param message the message
     * @param cause the cause
     */
    public WccWorkImportException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public WccWorkImportExceptionCodeEnum getErrorCode() {
		return errorCode;
	}


}
