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

import java.io.Serializable;
import java.util.Date;

import org.wipo.connect.common.dto.Identifiable;

public class IssueLog implements Serializable, Identifiable {

    private static final long serialVersionUID = -4246879822400607997L;

    private Long idIssueLog;

    private String message;
    private String stackTrace;
    private Date serverDate;
    private Date dateInsert;

    @Override
    public Long getId() {
	return getIdIssueLog();
    }

    @Override
    public void setId(Long id) {
	setIdIssueLog(id);
    }

    public Long getIdIssueLog() {
	return idIssueLog;
    }

    public void setIdIssueLog(Long idIssueLog) {
	this.idIssueLog = idIssueLog;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public String getStackTrace() {
	return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
	this.stackTrace = stackTrace;
    }

    public Date getServerDate() {
	return serverDate;
    }

    public void setServerDate(Date serverDate) {
	this.serverDate = serverDate;
    }

    public Date getDateInsert() {
	return dateInsert;
    }

    public void setDateInsert(Date dateInsert) {
	this.dateInsert = dateInsert;
    }

}
