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

import org.wipo.connect.common.dto.Identifiable;

/**
 * The Class WorkTaskDetailDerivedWork.
 */
public class WorkTaskDetailDerivedWork implements Serializable, Identifiable {

	private static final long serialVersionUID = -7120404170185360418L;

	private Long idWorkTaskDetailDerivedWork;

	private Long fkWorkTaskDetail;

	private Long weight;

	private String mainIdWork;

	private String sharedMainId;
	
	private Long trackNumber;

	@Override
	public Long getId() {
		return getIdWorkTaskDetailDerivedWork();
	}

	@Override
	public void setId(Long id) {
		setIdWorkTaskDetailDerivedWork(id);
	}

	public Long getIdWorkTaskDetailDerivedWork() {
		return idWorkTaskDetailDerivedWork;
	}

	public void setIdWorkTaskDetailDerivedWork(Long idWorkTaskDetailDerivedWork) {
		this.idWorkTaskDetailDerivedWork = idWorkTaskDetailDerivedWork;
	}

	public Long getFkWorkTaskDetail() {
		return fkWorkTaskDetail;
	}

	public void setFkWorkTaskDetail(Long fkWorkTaskDetail) {
		this.fkWorkTaskDetail = fkWorkTaskDetail;
	}

	public Long getWeight() {
		return weight;
	}

	public void setWeight(Long weight) {
		this.weight = weight;
	}

	public String getMainIdWork() {
		return mainIdWork;
	}

	public void setMainIdWork(String mainIdWork) {
		this.mainIdWork = mainIdWork;
	}

	public String getSharedMainId() {
		return sharedMainId;
	}

	public void setSharedMainId(String sharedMainId) {
		this.sharedMainId = sharedMainId;
	}

	public Long getTrackNumber() {
	    return trackNumber;
	}

	public void setTrackNumber(Long trackNumber) {
	    this.trackNumber = trackNumber;
	}

}
