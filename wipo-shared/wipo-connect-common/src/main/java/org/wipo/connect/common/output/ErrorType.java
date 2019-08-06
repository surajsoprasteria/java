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

package org.wipo.connect.common.output;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The Class ErrorType.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ErrorType", propOrder = { "errorCode", "errorType", "errorMessage" })
public class ErrorType {

	/** The error code. */
	@XmlElement(required = true)
	protected String errorCode;

	/** The error type. */
	@XmlElement(required = true)
	protected String errorType;

	/** The error message. */
	@XmlElement(required = true)
	protected String errorMessage;

	/**
	 * Gets the error code.
	 *
	 * @return the error code
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Sets the error code.
	 *
	 * @param errorCode the new error code
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * Gets the error type.
	 *
	 * @return the error type
	 */
	public String getErrorType() {
		return errorType;
	}

	/**
	 * Sets the error type.
	 *
	 * @param errorType the new error type
	 */
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	/**
	 * Gets the error message.
	 *
	 * @return the error message
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Sets the error message.
	 *
	 * @param errorMessage the new error message
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
