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

import java.util.Date;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class IssueLogFactory {

    private IssueLogFactory() {
	super();
    }

    public static IssueLog getIssueLog(String message, Throwable exception) {
	IssueLog issueLog = getIssueLog(message);

	issueLog.setStackTrace(ExceptionUtils.getStackTrace(exception));

	return issueLog;
    }

    public static IssueLog getIssueLog(String message) {
	IssueLog issueLog = new IssueLog();
	issueLog.setMessage(message);
	issueLog.setServerDate(new Date());
	return issueLog;
    }

    public static IssueLog getIssueLog(String format, Object... arguments) {
	String message = format;
	String stackTrace = null;
	Throwable ex = null;
	if (arguments != null) {
	    for (int i = 0; i < arguments.length; i++) {
		if (arguments[i] != null && arguments[i] instanceof Throwable) {
		    message = StringUtils.replaceOnce(message, "{}", ExceptionUtils.getStackTrace((Throwable) arguments[i]));
		    ex = (Throwable) arguments[i];
		    stackTrace = ExceptionUtils.getStackTrace(ex);
		} else {
		    message = StringUtils.replaceOnce(message, "{}", ObjectUtils.defaultIfNull(arguments[i], "").toString());
		}
	    }
	    message = getErrorMessage(message, ex);
	}
	IssueLog issueLog = getIssueLog(message);
	issueLog.setStackTrace(stackTrace);

	return issueLog;
    }

    public static String getErrorMessage(String msg, Throwable t) {
	Throwable aux = t;
	StringBuilder sb = new StringBuilder(msg);
	for (int i = 0; i < 3; i++) {
	    if (aux == null) {
		break;
	    }
	    sb.append(" <-- ");
	    sb.append(aux.getMessage());
	    aux = aux.getCause();
	}

	return sb.toString();
    }

}
