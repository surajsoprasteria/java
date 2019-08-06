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

import java.util.Map;

/**
 * The Class WccControllerException.
 *
 * @author fumagalli
 */
public class WccControllerException extends WccException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 135981510102575455L;

    public WccControllerException(WccExceptionCodeEnum code, String message, Map<String, Object> data) {
	super(code, message, data);
    }

    public WccControllerException(WccExceptionCodeEnum code, String message, Throwable cause, Map<String, Object> data) {
	super(code, message, cause, data);
    }

    public WccControllerException(WccExceptionCodeEnum code, String message, Throwable cause) {
	super(code, message, cause);
    }

    public WccControllerException(WccExceptionCodeEnum code, String message) {
	super(code, message);
    }

    public WccControllerException(WccExceptionCodeEnum code, Throwable cause, Map<String, Object> data) {
	super(code, cause, data);
    }

    public WccControllerException(WccExceptionCodeEnum code, Throwable cause) {
	super(code, cause);
    }

    public WccControllerException(Throwable cause) {
	super(cause);
    }

}
