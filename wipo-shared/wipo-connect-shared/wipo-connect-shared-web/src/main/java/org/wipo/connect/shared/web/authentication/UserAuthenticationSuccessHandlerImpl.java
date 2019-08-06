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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;
import org.wipo.connect.shared.exchange.dto.impl.UiLanguage;
import org.wipo.connect.shared.web.utils.SharedConfigUtils;

/**
 * The Class UserAuthenticationSuccessHandler.
 */
@Service
public class UserAuthenticationSuccessHandlerImpl extends SavedRequestAwareAuthenticationSuccessHandler implements UserAuthenticationSuccessHandler {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(UserAuthenticationSuccessHandlerImpl.class);

    @Autowired
    private LocaleResolver localeResolver;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {
	LOGGER.debug("User logged in successfully: " + auth.getName() + " - " + super.getDefaultTargetUrl());

	changeLocale(req, resp, auth);

	HttpSession session = req.getSession(false);
	if (session != null) {
	    session.setAttribute("clearStorage", true);
	}

	super.onAuthenticationSuccess(req, resp, auth);
    }

    @Override
    public void setDefaultTargetUrl(String defaultTargetUrl) {
	super.setDefaultTargetUrl(defaultTargetUrl);
    }

    private void changeLocale(HttpServletRequest req, HttpServletResponse resp, Authentication auth) throws IOException, ServletException {

	LOGGER.debug("User logged in successfully: " + auth.getName());

	Object principal = auth.getPrincipal();
	if (principal instanceof SecurityUserDetail) {
	    UiLanguage uiLanguage = ((SecurityUserDetail) principal).getUiLanguage();

	    if (uiLanguage != null && uiLanguage.getId() != null) {
		SharedConfigUtils.changeLocale(req, resp, uiLanguage.getUiLocale(), localeResolver);
		LOGGER.debug("Locale setup: [" + auth.getName() + " -> " + uiLanguage.getUiLocale() + "]");
	    } else {
		LOGGER.debug("Locale setup: [" + auth.getName() + " -> DEFAULT]");
	    }
	}
    }

    @Override
    public void setTargetUrlParameter(String targetUrlParameter) {
	super.setTargetUrlParameter(targetUrlParameter);
    }

}
