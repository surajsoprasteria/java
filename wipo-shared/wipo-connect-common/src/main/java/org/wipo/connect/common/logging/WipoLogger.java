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

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.springframework.context.ApplicationContext;
import org.wipo.connect.common.spring.SpringUtils;
import org.wipo.connect.common.utils.WccUtils;

public final class WipoLogger implements Logger {

    private final Logger logger;

    protected WipoLogger(Logger logger) {
	super();
	this.logger = logger;
    }

    private IssueLogManager getIssueLogManager() {
	IssueLogManager issueLogManager = null;
	try {
	    ApplicationContext ctx = SpringUtils.applicationContext;
	    if (ctx != null) {
		issueLogManager = SpringUtils.applicationContext.getBean(IssueLogManager.class);
	    }
	} catch (Exception e) {
	    logger.error("Error in " + WccUtils.getMethodName(), e);
	}

	if (issueLogManager == null) {
	    logger.warn("Error retrieving issueLogManager, using DummyIssueLogManager");
	    issueLogManager = new DummyIssueLogManager();
	}

	return issueLogManager;
    }

    @Override
    public String getName() {
	return logger.getName();
    }

    @Override
    public boolean isTraceEnabled() {
	return logger.isTraceEnabled();
    }

    @Override
    public void trace(String msg) {
	logger.trace(msg);
    }

    @Override
    public void trace(String format, Object arg) {
	logger.trace(format, arg);
    }

    @Override
    public void trace(String format, Object arg1, Object arg2) {
	logger.trace(format, arg1, arg2);
    }

    @Override
    public void trace(String format, Object... arguments) {
	logger.trace(format, arguments);
    }

    @Override
    public void trace(String msg, Throwable t) {
	logger.trace(msg, t);
    }

    @Override
    public boolean isTraceEnabled(Marker marker) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void trace(Marker marker, String msg) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void trace(Marker marker, String format, Object arg) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void trace(Marker marker, String format, Object arg1, Object arg2) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void trace(Marker marker, String format, Object... argArray) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void trace(Marker marker, String msg, Throwable t) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public boolean isDebugEnabled() {
	return logger.isDebugEnabled();
    }

    @Override
    public void debug(String msg) {
	logger.debug(msg);
    }

    @Override
    public void debug(String format, Object arg) {
	logger.debug(format, arg);
    }

    @Override
    public void debug(String format, Object arg1, Object arg2) {
	logger.debug(format, arg1, arg2);
    }

    @Override
    public void debug(String format, Object... arguments) {
	logger.debug(format, arguments);
    }

    @Override
    public void debug(String msg, Throwable t) {
	logger.debug(msg, t);
    }

    @Override
    public boolean isDebugEnabled(Marker marker) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void debug(Marker marker, String msg) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void debug(Marker marker, String format, Object arg) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void debug(Marker marker, String format, Object arg1, Object arg2) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void debug(Marker marker, String format, Object... arguments) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void debug(Marker marker, String msg, Throwable t) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public boolean isInfoEnabled() {
	return logger.isInfoEnabled();
    }

    @Override
    public void info(String msg) {
	logger.info(msg);
    }

    @Override
    public void info(String format, Object arg) {
	logger.info(format, arg);
    }

    @Override
    public void info(String format, Object arg1, Object arg2) {
	logger.info(format, arg1, arg2);
    }

    @Override
    public void info(String format, Object... arguments) {
	logger.info(format, arguments);
    }

    @Override
    public void info(String msg, Throwable t) {
	logger.info(msg, t);
    }

    @Override
    public boolean isInfoEnabled(Marker marker) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void info(Marker marker, String msg) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void info(Marker marker, String format, Object arg) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void info(Marker marker, String format, Object arg1, Object arg2) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void info(Marker marker, String format, Object... arguments) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void info(Marker marker, String msg, Throwable t) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public boolean isWarnEnabled() {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void warn(String msg) {
	logger.warn(msg);
    }

    @Override
    public void warn(String format, Object arg) {
	logger.warn(format, arg);
    }

    @Override
    public void warn(String format, Object... arguments) {
	logger.warn(format, arguments);
    }

    @Override
    public void warn(String format, Object arg1, Object arg2) {
	logger.warn(format, arg1, arg2);
    }

    @Override
    public void warn(String msg, Throwable t) {
	logger.warn(msg, t);
    }

    @Override
    public boolean isWarnEnabled(Marker marker) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void warn(Marker marker, String msg) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void warn(Marker marker, String format, Object arg) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void warn(Marker marker, String format, Object arg1, Object arg2) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void warn(Marker marker, String format, Object... arguments) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void warn(Marker marker, String msg, Throwable t) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public boolean isErrorEnabled() {
	return logger.isErrorEnabled();
    }

    @Override
    public void error(String msg) {
	logger.error(msg);
	getIssueLogManager().saveLog(IssueLogFactory.getIssueLog(msg));
    }

    @Override
    public void error(String format, Object arg) {
	logger.error(format, arg);
	getIssueLogManager().saveLog(IssueLogFactory.getIssueLog(format, arg));
    }

    public void errorNoSave(String format, Object arg) {
	logger.error(format, arg);
    }

    @Override
    public void error(String format, Object arg1, Object arg2) {
	this.error(format, new Object[] { arg1, arg2 });
    }

    @Override
    public void error(String format, Object... arguments) {
	logger.error(format, arguments);

	if (arguments != null && arguments.length > 0) {
	    Object last = arguments[arguments.length - 1];
	    if (last != null && last instanceof Throwable) {
		logger.error(IssueLogFactory.getErrorMessage(format, (Throwable) last));
		logger.warn(format, arguments);
	    } else {
		logger.error(format, arguments);
	    }
	}

	getIssueLogManager().saveLog(IssueLogFactory.getIssueLog(format, arguments));
    }

    @Override
    public void error(String msg, Throwable t) {
	logger.error(IssueLogFactory.getErrorMessage(msg, t));
	logger.warn(msg, t);
	try {
	    getIssueLogManager().saveLog(IssueLogFactory.getIssueLog(msg, t));
	} catch (Exception e) {
	    logger.error("Error in " + WccUtils.getMethodName(), e);
	}
    }

    @Override
    public boolean isErrorEnabled(Marker marker) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void error(Marker marker, String msg) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void error(Marker marker, String format, Object arg) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void error(Marker marker, String format, Object arg1, Object arg2) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void error(Marker marker, String format, Object... arguments) {
	throw new NotImplementedException("not implemented");
    }

    @Override
    public void error(Marker marker, String msg, Throwable t) {
	throw new NotImplementedException("not implemented");
    }

}
