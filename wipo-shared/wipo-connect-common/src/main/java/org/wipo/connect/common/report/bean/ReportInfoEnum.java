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
package org.wipo.connect.common.report.bean;

import java.util.Date;

public enum ReportInfoEnum {

    LOCAL_IP_REPORT("RS", "report_statements", "ReportStatements_", "localIpReport"),

    LOCAL_IP_REPORT_PDF("RSPDF", "report_statements", "LocalIpReport_", "localIpReportPDF"),

    FOREIGN_CMO_REPORT("RS", "report_statements", "ReportStatements_", "foreignCmosReport"),

    UP_REPORT("RS", "report_statements", "ReportStatements_", "upReport"),

    UDS_REPORT("RS", "report_statements", "ReportStatements_", "udsReport"),

    COLLECTION_REQUEST_REPORT("CRR", "Collection_Request_", "CollectionRequestReport_", "collectionRequestReport");

    private final String reportCode;

    private final String reportOutputFilename;

    private final String reportJasperName;

    private final String reportBeanName;

    private ReportInfoEnum(String reportCode, String reportOutputFilename, String reportJasperName, String reportBeanName) {
	this.reportOutputFilename = reportOutputFilename;
	this.reportCode = reportCode;
	this.reportJasperName = reportJasperName;
	this.reportBeanName = reportBeanName;
    }

    public String getReportCode() {
	return reportCode;
    }

    public String getReportOutputFilename() {
	return reportOutputFilename;
    }

    public String getReportJasperName() {
	return reportJasperName;
    }

    public String getReportBeanName() {
	return reportBeanName;
    }

    public String getUniqueOutputFilename() {
	String unique = reportOutputFilename + "_" + new Date(System.currentTimeMillis()).getTime();
	return unique;
    }

    public static ReportInfoEnum getTipoOutputByReportCode(String reportCode) {
	for (ReportInfoEnum tipoOutput : ReportInfoEnum.values()) {
	    if (tipoOutput.getReportCode().equals(reportCode)) {
		return tipoOutput;
	    }
	}
	return null;
    }

    public static String getDistributionReportFilename(ReportInfoEnum reportInfo, ReportTypeEnum reportType, String distributionCode, String roMainId, String cmoCode) {
	String path = "";
	if (reportInfo.equals(ReportInfoEnum.LOCAL_IP_REPORT) || reportInfo.equals(ReportInfoEnum.LOCAL_IP_REPORT_PDF)) {
	    path += "/domestic/" + reportType.getFolder() + "/";
	    path += distributionCode + "-" + roMainId;
	} else if (reportInfo.equals(ReportInfoEnum.FOREIGN_CMO_REPORT)) {
	    path += "/international/";
	    path += distributionCode + "-" + cmoCode;
	} else if (reportInfo.equals(ReportInfoEnum.UP_REPORT)) {
	    path += "/non-distributable/";
	    path += distributionCode + "-UP";
	} else if (reportInfo.equals(ReportInfoEnum.UDS_REPORT)) {
	    path += "/non-distributable/";
	    path += distributionCode + "-UDS";
	}
	return path;
    }

}
