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
 * The Class IpImportFile.
 */
public class IpImportFile implements Identifiable, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2001625360512997034L;

	/** The id ip import file. */
	private Long idIpImportFile;

	/** The fk ip import. */
	private Long fkIpImport;

	/** The file type. */
	private String fileType;

	/** The file name. */
	private String fileName;

	/** The file size. */
	private Long fileSize;

	/** The content type. */
	private String contentType;


	/**
	 * Gets the id ip import file.
	 *
	 * @return the id ip import file
	 */
	public Long getIdIpImportFile() {
		return idIpImportFile;
	}

	/**
	 * Sets the id ip import file.
	 *
	 * @param idIpImportFile the new id ip import file
	 */
	public void setIdIpImportFile(Long idIpImportFile) {
		this.idIpImportFile = idIpImportFile;
	}

	/**
	 * Gets the fk ip import.
	 *
	 * @return the fk ip import
	 */
	public Long getFkIpImport() {
		return fkIpImport;
	}

	/**
	 * Sets the fk ip import.
	 *
	 * @param fkIpImport the new fk ip import
	 */
	public void setFkIpImport(Long fkIpImport) {
		this.fkIpImport = fkIpImport;
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
	 * Sets the file size.
	 *
	 * @param fileSize the new file size
	 */
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * Sets the content type.
	 *
	 * @param contentType the new content type
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
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


	@Override
	public Long getId() {
		return getIdIpImportFile();
	}


	@Override
	public void setId(Long id) {
		setIdIpImportFile(id);
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
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Gets the file size.
	 *
	 * @return the file size
	 */
	public Long getFileSize() {
		return fileSize;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toStringExclude(this, "document");
	}

}
