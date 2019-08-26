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

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The Class IpImportFile.
 */

//import_code, fk_status, start_date, end_date
@Entity
@Table(name = "ip_import")
public class IpImport {

	/** The id ip import. */
	@Id
	@Column(name = "ID_IP_IMPORT")
	private Long idIpImport;

	/** The import code. */
	@Column(name = "IMPORT_CODE")
	private String importCode;

	/** The fk status. */
	@Column(name = "FK_STATUS")
	private Long fkStatus;

	/** The start date. */
	@Column(name = "START_DATE")
	private Date startDate;

	/** The end date. */
	@Column(name = "END_DATE")
	private Date endDate;

	/** The row count. */
	@Column(name = "ROW_COUNT")
	private int rowCount;

	/** The inserted items. */
	@Column(name = "INSERTED_ITEMS")
	private int insertedItems;

	/** The lines with errors. */
	@Column(name = "LINES_WITH_ERRORS")
	private int linesWithErrors;

	public IpImport() {
		super();
	}

	public Long getIdIpImport() {
		return idIpImport;
	}

	public void setIdIpImport(Long idIpImport) {
		this.idIpImport = idIpImport;
	}

	public String getImportCode() {
		return importCode;
	}

	public void setImportCode(String importCode) {
		this.importCode = importCode;
	}

	public Long getFkStatus() {
		return fkStatus;
	}

	public void setFkStatus(Long fkStatus) {
		this.fkStatus = fkStatus;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getInsertedItems() {
		return insertedItems;
	}

	public void setInsertedItems(int insertedItems) {
		this.insertedItems = insertedItems;
	}

	public int getLinesWithErrors() {
		return linesWithErrors;
	}

	public void setLinesWithErrors(int linesWithErrors) {
		this.linesWithErrors = linesWithErrors;
	}

}
