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

package org.wipo.connect.shared.exchange.restvo.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.wipo.connect.shared.exchange.enumerator.ExportTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.FileFormatEnum;

/**
 * The Class FindByIdRestVO.
 */
public class FindByIdListRestVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2881303384280965565L;
    /** The id list. */
    private List<Long> idList;

    private ExportTypeEnum exportType;

    private FileFormatEnum fileFormatEnum;

    /** The fetch document list. */
    private boolean fetchDocumentList;

    /**
     * Gets the id list.
     *
     * @return the id list
     */
    public List<Long> getIdList() {
	if (null == idList) {
	    idList = new ArrayList<Long>();
	}
	return idList;
    }

    /**
     * Sets the id list.
     *
     * @param idList
     *            the new id list
     */
    public void setIdList(List<Long> idList) {
	this.idList = idList;
    }

    /**
     * Checks if is fetch document list.
     *
     * @return true, if is fetch document list
     */
    public boolean isFetchDocumentList() {
	return fetchDocumentList;
    }

    /**
     * Sets the fetch document list.
     *
     * @param fetchDocumentList
     *            the new fetch document list
     */
    public void setFetchDocumentList(boolean fetchDocumentList) {
	this.fetchDocumentList = fetchDocumentList;
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
