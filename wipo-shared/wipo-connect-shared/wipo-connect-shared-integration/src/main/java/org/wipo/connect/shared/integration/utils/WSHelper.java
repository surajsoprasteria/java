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

package org.wipo.connect.shared.integration.utils;

import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.input.ContextType;
import org.wipo.connect.common.output.ErrorType;

/**
 * The Class WSHelper.
 */
public class WSHelper {

	private WSHelper() {
		super();
	}

	/**
	 * Generate ws error response.
	 *
	 * @param message the message
	 * @return the error type
	 */
	public static ErrorType generateWsErrorResponse(String message) {
		ErrorType error = new ErrorType();
		error.setErrorCode(WccExceptionCodeEnum.WEBSERVICE_ERROR.getCode());
		error.setErrorType(WccExceptionCodeEnum.WEBSERVICE_ERROR.toString());
		error.setErrorMessage(message);
		return error;
	}

	/**
	 * Generate ws auth error response.
	 *
	 * @param context the context
	 * @return the error type
	 */
	public static ErrorType generateWsAuthErrorResponse(ContextType context) {
		ErrorType error = new ErrorType();
		error.setErrorCode(WccExceptionCodeEnum.WEBSERVICE_AUTHENTICATION_ERROR.getCode());
		error.setErrorType(WccExceptionCodeEnum.WEBSERVICE_AUTHENTICATION_ERROR.toString());
		error.setErrorMessage("Invalid client code/key (" + context.getClientId() + ")");
		return error;
	}

	/**
	 * Generate too many results error response.
	 *
	 * @return the error type
	 */
	public static ErrorType generateTooManyResultsErrorResponse() {
		ErrorType error = new ErrorType();
		error.setErrorCode(WccExceptionCodeEnum.TOO_MANY_RESULTS.getCode());
		error.setErrorType(WccExceptionCodeEnum.TOO_MANY_RESULTS.toString());
		error.setErrorMessage("Your search returned too many results, please specify more restrictive search parameters");
		return error;
	}

}
