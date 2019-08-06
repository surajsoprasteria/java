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

package org.wipo.connect.shared.exchange.restvo.interestedParty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.wipo.connect.shared.exchange.enumerator.ExportTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.FileFormatEnum;

/**
 * The Class FindByIpListRestVO.
 */
public class FindByIpIdListRestVO implements Serializable {

    private static final long serialVersionUID = 8361603050099964672L;

    /** The ip list. */
    private List<Long> ipList;
    private ExportTypeEnum exportType;
    private FileFormatEnum fileFormatEnum;

    public List<Long> getIpList() {
	if (ipList == null) {
	    ipList = new ArrayList<Long>();
	}
	return ipList;
    }

    public void setIpList(List<Long> ipList) {
	this.ipList = ipList;
    }

    public ExportTypeEnum getExportType() {
	return exportType;
    }

    public void setExportType(ExportTypeEnum exportType) {
	this.exportType = exportType;
    }

    public FileFormatEnum getFileFormatEnum() {
	return fileFormatEnum;
    }

    public void setFileFormatEnum(FileFormatEnum fileFormatEnum) {
	this.fileFormatEnum = fileFormatEnum;
    }

}
