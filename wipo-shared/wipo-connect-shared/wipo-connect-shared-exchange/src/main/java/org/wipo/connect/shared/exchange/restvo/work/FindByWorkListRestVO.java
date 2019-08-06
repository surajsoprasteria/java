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

package org.wipo.connect.shared.exchange.restvo.work;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.enumerator.ExportTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.FileFormatEnum;

/**
 * The Class FindByWorkListRestVO.
 */
public class FindByWorkListRestVO implements Serializable {

    private static final long serialVersionUID = 8361603050099964672L;

    /** The work list. */
    private List<Work> workList;
    private ExportTypeEnum exportType;
    private FileFormatEnum fileFormatEnum;
    private String filePath;

    /**
     * Gets the work list.
     *
     * @return the work list
     */
    public List<Work> getWorkList() {
	if (workList == null) {
	    workList = new ArrayList<Work>();
	}
	return workList;
    }

    /**
     * Sets the work list.
     *
     * @param workList
     *            the new work list
     */
    public void setWorkList(List<Work> workList) {
	this.workList = workList;
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

    public String getFilePath() {
	return filePath;
    }

    public void setFilePath(String filePath) {
	this.filePath = filePath;
    }

}
