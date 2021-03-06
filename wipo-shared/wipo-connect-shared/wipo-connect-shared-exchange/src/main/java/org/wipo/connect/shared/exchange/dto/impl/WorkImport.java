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

package org.wipo.connect.shared.exchange.dto.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.customvalidation.CustomValidatedField;
import org.wipo.connect.common.dto.Identifiable;
import org.wipo.connect.shared.exchange.enumerator.ImportFileTypeEnum;

/**
 * The Class WorkImport.
 *
 * @author minervini
 */
public class WorkImport implements Identifiable, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4008574286300015835L;

	/** The id work import. */
	private Long idWorkImport;

	/** The import code. */
	private String importCode;

	/** The fk status. */
	private Long fkStatus;

	/** The start date. */
	private Date startDate;

	/** The end date. */
	private Date endDate;

	/** The row count. */
	private int rowCount;
	
	/** The inserted items. */
	private int insertedItems;
	
	/** The lines with errors. */
	private int linesWithErrors;

	/**
	 * Gets the row count.
	 *
	 * @return the row count
	 */
	public int getRowCount() {
		return rowCount;
	}

	/**
	 * Sets the row count.
	 *
	 * @param rowCount the new row count
	 */
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	/**
	 * Gets the inserted items.
	 *
	 * @return the inserted items
	 */
	public int getInsertedItems() {
		return insertedItems;
	}

	/**
	 * Sets the inserted items.
	 *
	 * @param insertedItems the new inserted items
	 */
	public void setInsertedItems(int insertedItems) {
		this.insertedItems = insertedItems;
	}

	/**
	 * Gets the lines with errors.
	 *
	 * @return the lines with errors
	 */
	public int getLinesWithErrors() {
		return linesWithErrors;
	}

	/**
	 * Sets the lines with errors.
	 *
	 * @param linesWithErrors the new lines with errors
	 */
	public void setLinesWithErrors(int linesWithErrors) {
		this.linesWithErrors = linesWithErrors;
	}

	/** The work import file list. */
	@CustomValidatedField(innerValidation = true)
	private List<WorkImportFile> workImportFileList;

	/**
	 * Gets the import code.
	 *
	 * @return the import code
	 */
	public String getImportCode() {
		return importCode;
	}

	/**
	 * Sets the import code.
	 *
	 * @param importCode the new import code
	 */
	public void setImportCode(String importCode) {
		this.importCode = importCode;
	}

	/**
	 * Gets the fk status.
	 *
	 * @return the fk status
	 */
	public Long getFkStatus() {
		return fkStatus;
	}

	/**
	 * Sets the fk status.
	 *
	 * @param fkStatus the new fk status
	 */
	public void setFkStatus(Long fkStatus) {
		this.fkStatus = fkStatus;
	}

	/**
	 * Gets the work import file list.
	 *
	 * @return the work import file list
	 */
	public List<WorkImportFile> getWorkImportFileList() {
		if (null == workImportFileList) {
			workImportFileList = new ArrayList<WorkImportFile>();
		}
		return workImportFileList;
	}

	/**
	 * Sets the work import file list.
	 *
	 * @param workImportFileList the new work import file list
	 */
	public void setWorkImportFileList(List<WorkImportFile> workImportFileList) {
		this.workImportFileList = workImportFileList;
	}

	/**
	 * Gets the input file.
	 *
	 * @return the input file
	 */
	public WorkImportFile getInputFile() {
		for (WorkImportFile f : getWorkImportFileList()) {
			if (f.getFileType().equals(ImportFileTypeEnum.INPUT.name())) {
				return f;
			}
		}
		return null;
	}

	/**
	 * Gets the output file.
	 *
	 * @return the output file
	 */
	public WorkImportFile getOutputFile() {
		for (WorkImportFile f : getWorkImportFileList()) {
			if (f.getFileType().equals(ImportFileTypeEnum.OUTPUT.name())) {
				return f;
			}
		}
		return null;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}


	@Override
	public Long getId() {
		return getIdWorkImport();
	}


	@Override
	public void setId(Long id) {
		setIdWorkImport(id);
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the id work import.
	 *
	 * @return the id work import
	 */
	public Long getIdWorkImport() {
		return idWorkImport;
	}

	/**
	 * Sets the id work import.
	 *
	 * @param idWorkImport the new id work import
	 */
	public void setIdWorkImport(Long idWorkImport) {
		this.idWorkImport = idWorkImport;
	}

}
