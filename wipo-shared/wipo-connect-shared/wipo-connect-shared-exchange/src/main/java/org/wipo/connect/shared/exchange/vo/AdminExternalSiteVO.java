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

package org.wipo.connect.shared.exchange.vo;

import java.io.Serializable;

import org.wipo.connect.shared.exchange.dto.impl.ExternalSite;

public class AdminExternalSiteVO implements Serializable {

    private static final long serialVersionUID = -6084834506039420049L;

    private ExternalSite ipsFTP;

    private ExternalSite worksFTP;

    private ExternalSite workSubmissionsFTP;

    private ExternalSite ipSubmissionsFTP;

    private ExternalSite ipMassiveExportFTP;

    private ExternalSite workMassiveExportFTP;

    private ExternalSite queryImportFTP;

    public ExternalSite getIpSubmissionsFTP() {
	return ipSubmissionsFTP;
    }

    public void setIpSubmissionsFTP(ExternalSite ipSubmissionsFTP) {
	this.ipSubmissionsFTP = ipSubmissionsFTP;
    }

    public ExternalSite getIpsFTP() {
	if (null == ipsFTP) {
	    ipsFTP = new ExternalSite();
	}
	return ipsFTP;
    }

    public void setIpsFTP(ExternalSite ipsFTP) {
	this.ipsFTP = ipsFTP;
    }

    public ExternalSite getWorksFTP() {
	if (null == worksFTP) {
	    worksFTP = new ExternalSite();
	}
	return worksFTP;
    }

    public void setWorksFTP(ExternalSite worksFTP) {
	this.worksFTP = worksFTP;
    }

    public ExternalSite getWorkSubmissionsFTP() {
	if (null == workSubmissionsFTP) {
	    workSubmissionsFTP = new ExternalSite();
	}
	return workSubmissionsFTP;
    }

    public void setWorkSubmissionsFTP(ExternalSite workSubmissionsFTP) {
	this.workSubmissionsFTP = workSubmissionsFTP;
    }

    public ExternalSite getIpMassiveExportFTP() {
	if (null == ipMassiveExportFTP) {
	    ipMassiveExportFTP = new ExternalSite();
	}
	return ipMassiveExportFTP;
    }

    public void setIpMassiveExportFTP(ExternalSite ipMassiveExportFTP) {
	this.ipMassiveExportFTP = ipMassiveExportFTP;
    }

    public ExternalSite getWorkMassiveExportFTP() {
	if (null == workMassiveExportFTP) {
	    workMassiveExportFTP = new ExternalSite();
	}
	return workMassiveExportFTP;
    }

    public void setWorkMassiveExportFTP(ExternalSite workMassiveExportFTP) {
	this.workMassiveExportFTP = workMassiveExportFTP;
    }

    public ExternalSite getQueryImportFTP() {
	if (null == queryImportFTP) {
	    queryImportFTP = new ExternalSite();
	}
	return queryImportFTP;
    }

    public void setQueryImportFTP(ExternalSite queryImportFTP) {
	this.queryImportFTP = queryImportFTP;
    }

}
