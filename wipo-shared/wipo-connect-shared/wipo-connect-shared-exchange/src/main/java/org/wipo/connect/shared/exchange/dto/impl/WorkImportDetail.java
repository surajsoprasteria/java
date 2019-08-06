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

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * The Class WorkImportDetail.
 *
 * @author minervini
 */
public class WorkImportDetail implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7656557125335726514L;

	/** The id work import detail. */
	private Long idWorkImportDetail;

	/** The fk work import. */
	private Long fkWorkImport;

	/** The fk work. */
	private Long fkWork;

	/** The fk status. */
	private Long fkStatus;

	/** The status. */
	private String status;

	/** The row number. */
	private int rowNumber;

	/** The row id. */
	private String rowId;

	/** The row type. */
	private String rowType;

	/** The transaction. */
	private String transaction;

	/** The wipo connect import id. */
	private String wipoConnectImportId;

	/** The error cause. */
	private String errorCause;

	/**
	 * Gets the row type.
	 *
	 * @return the row type
	 */
	public String getRowType() {
		return rowType;
	}

	/**
	 * Sets the row type.
	 *
	 * @param rowType the new row type
	 */
	public void setRowType(String rowType) {
		this.rowType = rowType;
	}

	/**
	 * Gets the id work import detail.
	 *
	 * @return the id work import detail
	 */
	public Long getIdWorkImportDetail() {
		return idWorkImportDetail;
	}

	/**
	 * Sets the id work import detail.
	 *
	 * @param idWorkImportDetail the new id work import detail
	 */
	public void setIdWorkImportDetail(Long idWorkImportDetail) {
		this.idWorkImportDetail = idWorkImportDetail;
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
	 * Gets the fk work.
	 *
	 * @return the fk work
	 */
	public Long getFkWork() {
		return fkWork;
	}

	/**
	 * Sets the fk work.
	 *
	 * @param fkWork the new fk work
	 */
	public void setFkWork(Long fkWork) {
		this.fkWork = fkWork;
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
	 * Gets the row number.
	 *
	 * @return the row number
	 */
	public int getRowNumber() {
		return rowNumber;
	}

	/**
	 * Sets the row number.
	 *
	 * @param rowNumber the new row number
	 */
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	/**
	 * Gets the row id.
	 *
	 * @return the row id
	 */
	public String getRowId() {
		return rowId;
	}

	/**
	 * Sets the row id.
	 *
	 * @param rowId the new row id
	 */
	public void setRowId(String rowId) {
		this.rowId = rowId;
	}


	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the transaction.
	 *
	 * @return the transaction
	 */
	public String getTransaction() {
		return transaction;
	}

	/**
	 * Sets the transaction.
	 *
	 * @param transaction the new transaction
	 */
	public void setTransaction(String transaction) {
		this.transaction = transaction;
	}


	/**
	 * Gets the wipo connect import id.
	 *
	 * @return the wipo connect import id
	 */
	public String getWipoConnectImportId() {
		return wipoConnectImportId;
	}

	/**
	 * Sets the wipo connect import id.
	 *
	 * @param wipoConnectImportId the new wipo connect import id
	 */
	public void setWipoConnectImportId(String wipoConnectImportId) {
		this.wipoConnectImportId = wipoConnectImportId;
	}

	/**
	 * Gets the error cause.
	 *
	 * @return the error cause
	 */
	public String getErrorCause() {
		return errorCause;
	}

	/**
	 * Sets the error cause.
	 *
	 * @param errorCause the new error cause
	 */
	public void setErrorCause(String errorCause) {
		this.errorCause = errorCause;
	}


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }


}
