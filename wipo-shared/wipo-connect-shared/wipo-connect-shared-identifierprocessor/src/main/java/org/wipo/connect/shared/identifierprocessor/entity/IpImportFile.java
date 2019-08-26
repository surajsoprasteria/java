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

package org.wipo.connect.shared.identifierprocessor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class IpImportFile.
 */
@Entity
@Table(name = "ip_import_file")
public class IpImportFile {

	/** The id ip import file. */
	@Id
	@Column(name = "ID_IP_IMPORT_FILE")
	private Long idIpImportFile;

	/** The fk ip import. */
	@Column(name = "FK_IP_IMPORT")
	private Long fkIpImport;

	/** The file type. */
	@Column(name = "FILE_TYPE")
	private String fileType;

	/** The file name. */
	@Column(name = "FILENAME")
	private String fileName;

	/** The file size. */
	@Column(name = "FILESIZE")
	private Long fileSize;

	/** The content type. */
	@Column(name = "CONTENT_TYPE")
	private String contentType;

	public IpImportFile() {
		super();
	}

	public Long getIdIpImportFile() {
		return idIpImportFile;
	}

	public void setIdIpImportFile(Long idIpImportFile) {
		this.idIpImportFile = idIpImportFile;
	}

	public Long getFkIpImport() {
		return fkIpImport;
	}

	public void setFkIpImport(Long fkIpImport) {
		this.fkIpImport = fkIpImport;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}
