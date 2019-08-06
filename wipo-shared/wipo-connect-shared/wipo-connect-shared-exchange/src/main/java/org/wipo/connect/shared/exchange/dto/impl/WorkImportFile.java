/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */

package org.wipo.connect.shared.exchange.dto.impl;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Identifiable;

/**
 * The Class WorkImportFile.
 */
public class WorkImportFile implements Identifiable, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2001625360512997034L;

	/** The id work import file. */
	private Long idWorkImportFile;

	/** The fk work import. */
	private Long fkWorkImport;

	/** The file type. */
	private String fileType;

	/** The file name. */
	private String fileName;

	/** The file size. */
	private Long fileSize;

	/** The content type. */
	private String contentType;


	/**
	 * Gets the id work import file.
	 *
	 * @return the id work import file
	 */
	public Long getIdWorkImportFile() {
		return idWorkImportFile;
	}

	/**
	 * Sets the id work import file.
	 *
	 * @param idWorkImportFile the new id work import file
	 */
	public void setIdWorkImportFile(Long idWorkImportFile) {
		this.idWorkImportFile = idWorkImportFile;
	}

	/**
	 * Gets the fk work import.
	 *
	 * @return the fk work import
	 */
	public Long getFkWorkImport() {
		return fkWorkImport;
	}

	/**
	 * Sets the fk work import.
	 *
	 * @param fkWorkImport the new fk work import
	 */
	public void setFkWorkImport(Long fkWorkImport) {
		this.fkWorkImport = fkWorkImport;
	}

	/**
	 * Gets the file type.
	 *
	 * @return the file type
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * Sets the file type.
	 *
	 * @param fileType the new file type
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * Gets the file size.
	 *
	 * @return the file size
	 */
	public Long getFileSize() {
		return fileSize;
	}

	/**
	 * Sets the file size.
	 *
	 * @param fileSize the new file size
	 */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * Gets the content type.
	 *
	 * @return the content type
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * Sets the content type.
	 *
	 * @param contentType the new content type
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this, "document");
	}


	@Override
	public Long getId() {
		return getIdWorkImportFile();
	}


	@Override
	public void setId(Long id) {
		setIdWorkImportFile(id);
	}
}
