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
package org.wipo.connect.shared.exchange.utils.importexport;

import org.wipo.connect.common.dto.IDownloadable;

/**
 * The Class ExportBean.
 */
public class ExportBean implements IDownloadable {

    private byte[] document;
    private String fileName;
    private Long fileSize;
    private String contentType;
    private String completePath;

    @Override
    public byte[] getDocument() {
	return document;
    }

    /**
     * Sets the document.
     *
     * @param document
     *            the new document
     */
    public void setDocument(byte[] document) {
	this.document = document;
    }

    @Override
    public String getFileName() {
	return fileName;
    }

    /**
     * Sets the file name.
     *
     * @param fileName
     *            the new file name
     */
    public void setFileName(String fileName) {
	this.fileName = fileName;
    }

    @Override
    public Long getFileSize() {
	return fileSize;
    }

    /**
     * Sets the file size.
     *
     * @param fileSize
     *            the new file size
     */
    public void setFileSize(Long fileSize) {
	this.fileSize = fileSize;
    }

    @Override
    public String getContentType() {
	return contentType;
    }

    /**
     * Sets the content type.
     *
     * @param contentType
     *            the new content type
     */
    public void setContentType(String contentType) {
	this.contentType = contentType;
    }

    public String getCompletePath() {
	return completePath;
    }

    public void setCompletePath(String completePath) {
	this.completePath = completePath;
    }

}
