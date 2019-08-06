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
package org.wipo.connect.shared.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import org.wipo.connect.common.logging.WipoLoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = WipoLoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(HttpClientErrorException.class)
    public RedirectView handleHttpClientErrorException(Exception cause, HttpServletRequest request, HttpServletResponse response) {
	logger.error("HttpClientErrorException handler executed", cause);

	RedirectView rw = new RedirectView("/mvc/common/login", true);
	FlashMap outputFlashMap = RequestContextUtils.getOutputFlashMap(request);
	if (outputFlashMap != null) {
	    outputFlashMap.put("isBackendDown", true);
	}

	return rw;
    }

    @ExceptionHandler(ResourceAccessException.class)
    public RedirectView handleResourceAccessException(Exception cause, HttpServletRequest request, HttpServletResponse response) {
	logger.error("HttpClientErrorException handler executed", cause);

	RedirectView rw = new RedirectView("/mvc/common/login", true);
	FlashMap outputFlashMap = RequestContextUtils.getOutputFlashMap(request);
	if (outputFlashMap != null) {
	    outputFlashMap.put("isBackendDown", true);
	}

	return rw;
    }
}
