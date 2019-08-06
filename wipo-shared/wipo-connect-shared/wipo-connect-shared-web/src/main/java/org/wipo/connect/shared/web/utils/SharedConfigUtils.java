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

package org.wipo.connect.shared.web.utils;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.springframework.web.servlet.LocaleResolver;

public class SharedConfigUtils {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(SharedConfigUtils.class);

    /** The Constant CODE_DATE_FORMAT. */
    public static final String CODE_DATE_FORMAT = "config.date-format";

    private SharedConfigUtils() {
	super();
    }

    public static void changeLocale(HttpServletRequest req, HttpServletResponse resp, Locale locale,
	    LocaleResolver localeResolver) throws IOException, ServletException {

	localeResolver.setLocale(req, resp, locale);
    }
}
