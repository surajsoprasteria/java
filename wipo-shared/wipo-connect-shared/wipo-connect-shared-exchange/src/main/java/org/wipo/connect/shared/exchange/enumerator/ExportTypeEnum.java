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
package org.wipo.connect.shared.exchange.enumerator;

public enum ExportTypeEnum {
    IP_LIST_VIEW_EXPORT("path.rightOwner.export-dir", ""),
    IP_FULL_EXPORT("path.rightOwner.export-dir", "path.rightOwner.sftpExport-dir"),
    WORK_LIST_VIEW_EXPORT("path.work.export-dir", ""),
    WORK_FULL_EXPORT("path.work.export-dir", "path.work.sftpExport-dir"),
    BI_EXPORT("path.bi.export-dir", "path.bi.sftpImport-dir");

    private String pathPropertyName;
    private String sftpPathPropertyName;

    private ExportTypeEnum(String pathPropertyName, String sftpPathPropertyName) {
	this.pathPropertyName = pathPropertyName;
	this.sftpPathPropertyName = sftpPathPropertyName;
    }

    public String getPathPropertyName() {
	return pathPropertyName;
    }

    public void setPathPropertyName(String pathPropertyName) {
	this.pathPropertyName = pathPropertyName;
    }

    public String getSftpPathPropertyName() {
	return sftpPathPropertyName;
    }

    public void setSftpPathPropertyName(String sftpPathPropertyName) {
	this.sftpPathPropertyName = sftpPathPropertyName;
    }

}
