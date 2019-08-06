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
package org.wipo.connect.common.logging;

import org.slf4j.Logger;

public final class WipoLoggerFactory {

    private WipoLoggerFactory() {
	super();
    }

    public static Logger getLogger(Class<?> clazz) {
	Logger baseLogger = org.slf4j.LoggerFactory.getLogger(clazz);
	return new WipoLogger(baseLogger);
    }

    public static WipoLogger getWipoLogger(Class<?> clazz) {
	Logger baseLogger = org.slf4j.LoggerFactory.getLogger(clazz);
	return new WipoLogger(baseLogger);
    }

}
