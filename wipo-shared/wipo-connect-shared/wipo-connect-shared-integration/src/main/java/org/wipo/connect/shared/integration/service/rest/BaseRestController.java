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

package org.wipo.connect.shared.integration.service.rest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.wipo.connect.common.exception.WccException;
import org.wipo.connect.common.exception.WccExceptionCodeEnum;
import org.wipo.connect.common.exception.WccExceptionFactory;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.wipo.connect.common.utils.WccUtils;

/**
 * The Class BaseRestController.
 */
@RestController
public class BaseRestController {

    /** The Constant LOGGER. */
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(BaseRestController.class);

    /**
     * Exception handler.
     *
     * @param exception
     *            the exception
     * @param response
     *            the response
     * @return the map
     */
    @ExceptionHandler(Exception.class)
    public Map<String, Object> exceptionHandler(Exception exception, HttpServletResponse response) {

	LOGGER.error("REST Controller Exception:", exception);

	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

	Map<String, Object> exceptionMap = new HashMap<>();
	exceptionMap.put("message", exception.getMessage());
	exceptionMap.put("code", WccExceptionCodeEnum.UNKNOW_ERROR);
	exceptionMap.put("data", null);
	if (exception.getCause() != null) {
	    exceptionMap.put("causeMessage", exception.getCause().getMessage());
	}
	exceptionMap.put("_isError", true);
	exceptionMap.put(WccExceptionFactory.CLASS_TYPE_FIELD, WccException.class.toString());

	return exceptionMap;
    }

    /**
     * Wcc exception handler.
     *
     * @param exception
     *            the exception
     * @param response
     *            the response
     * @return the map
     */
    @ExceptionHandler(WccException.class)
    public Map<String, Object> wccExceptionHandler(WccException exception, HttpServletResponse response) {

	LOGGER.error("REST Controller Exception:", exception);

	response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

	Map<String, Object> exceptionMap = new HashMap<>();
	try {
	    exceptionMap.putAll(WccUtils.objToMap(exception));
	} catch (WccException e) {
	    LOGGER.error("Error ", e);
	}
	exceptionMap.put("_isError", true);

	exceptionMap.put(WccExceptionFactory.CLASS_TYPE_FIELD, exception.getClass());

	return exceptionMap;
    }

}
