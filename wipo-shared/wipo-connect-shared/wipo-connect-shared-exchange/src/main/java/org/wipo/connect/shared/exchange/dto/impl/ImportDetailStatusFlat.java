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

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * The Class InterestedPartyStatusFlat.
 */
public class ImportDetailStatusFlat {

	/** The id import detail status. */
	private Long idImportDetailStatus;

	/** The value. */
	private String value;

	/** The code. */
	private String code;

	/** The sort order. */
	private Integer sortOrder;

	/**
	 * Gets the id import detail status.
	 *
	 * @return the id import detail status
	 */
	public Long getIdImportDetailStatus() {
		return idImportDetailStatus;
	}

	/**
	 * Sets the id import detail status.
	 *
	 * @param idImportDetailStatus the new id import detail status
	 */
	public void setIdImportDetailStatus(Long idImportDetailStatus) {
		this.idImportDetailStatus = idImportDetailStatus;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}


	/**
	 * Gets the sort order.
	 *
	 * @return the sort order
	 */
	public Integer getSortOrder() {
		return sortOrder;
	}

	/**
	 * Sets the sort order.
	 *
	 * @param sortOrder the new sort order
	 */
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
