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
package org.wipo.connect.shared.web.authentication;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.stereotype.Service;
import org.wipo.connect.common.logging.WipoLoggerFactory;

@Service
public class UserAuthenticationFailureHandlerImpl extends ExceptionMappingAuthenticationFailureHandler implements UserAuthenticationFailureHandler {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(UserAuthenticationFailureHandlerImpl.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
	LOGGER.debug(exception.getMessage());
	Map<String, String> failureUrlMap = new HashMap<>();
	failureUrlMap.put(DisabledException.class.getName(), "/mvc/common/login?userDisabled=true");
	setDefaultFailureUrl("/mvc/common/login?loginError=true");
	setExceptionMappings(failureUrlMap);
	super.onAuthenticationFailure(request, response, exception);
    }

}
