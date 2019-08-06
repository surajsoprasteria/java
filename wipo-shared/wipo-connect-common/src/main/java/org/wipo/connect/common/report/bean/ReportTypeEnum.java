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

public enum ReportTypeEnum {

    PDF("PDF", ".pdf", "PDF", "application/pdf"),

    XLS("Excel", ".xlsx", "XLSX", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),

    CSV("CSV", ".csv", "CSV", "text/csv");

    private String value;
    private String fileExtension;
    private String folder;
    private String contentType;

    private ReportTypeEnum(String value, String fileExtension, String folder, String contentType) {
	this.value = value;
	this.fileExtension = fileExtension;
	this.folder = folder;
	this.contentType = contentType;
    }

    public String getValue() {
	return value;
    }

    public String getFileExtension() {
	return fileExtension;
    }

    public String getFolder() {
	return folder;
    }

    public void setFolder(String folder) {
	this.folder = folder;
    }

    public String getContentType() {
	return contentType;
    }

    public static ReportTypeEnum getTipoReportByValue(String value) {
	for (ReportTypeEnum tipoReport : ReportTypeEnum.values()) {
	    if (tipoReport.getValue().equals(value)) {
		return tipoReport;
	    }
	}
	return null;
    }
}
