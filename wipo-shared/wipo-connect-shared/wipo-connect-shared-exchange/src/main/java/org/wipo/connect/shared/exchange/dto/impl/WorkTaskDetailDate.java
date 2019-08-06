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
import java.util.Date;

import org.wipo.connect.common.dto.Identifiable;

/**
 * The Class WorkTaskDetailDate.
 */
public class WorkTaskDetailDate implements Serializable, Identifiable {

	private static final long serialVersionUID = 8852323845057405696L;

	private Long idWorkTaskDetailDate;

	private Long fkWorkTaskDetail;

	private String dateTypeCode;

	private Date dateValue;

	@Override
	public Long getId() {
		return getIdWorkTaskDetailDate();
	}

	@Override
	public void setId(Long id) {
		setIdWorkTaskDetailDate(id);
	}

	public Long getIdWorkTaskDetailDate() {
		return idWorkTaskDetailDate;
	}

	public void setIdWorkTaskDetailDate(Long idWorkTaskDetailDate) {
		this.idWorkTaskDetailDate = idWorkTaskDetailDate;
	}

	public Long getFkWorkTaskDetail() {
		return fkWorkTaskDetail;
	}

	public void setFkWorkTaskDetail(Long fkWorkTaskDetail) {
		this.fkWorkTaskDetail = fkWorkTaskDetail;
	}

	public String getDateTypeCode() {
		return dateTypeCode;
	}

	public void setDateTypeCode(String dateTypeCode) {
		this.dateTypeCode = dateTypeCode;
	}

	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

}
