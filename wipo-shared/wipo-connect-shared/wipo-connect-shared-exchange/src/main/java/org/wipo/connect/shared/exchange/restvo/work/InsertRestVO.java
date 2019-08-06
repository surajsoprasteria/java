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

package org.wipo.connect.shared.exchange.restvo.work;

import java.io.Serializable;
import java.util.List;

import org.wipo.connect.shared.exchange.dto.impl.Work;
import org.wipo.connect.shared.exchange.dto.impl.WorkTaskDetail;

/**
 * The Class InsertRestVO.
 */
public class InsertRestVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6497323720517092513L;

	/** The work task detail list. */
	private List<WorkTaskDetail> workTaskDetailList;
	
	/** The work task detail. */
	private WorkTaskDetail workTaskDetail;
	
	/** The work. */
	private Work work;

	/**
	 * Gets the work task detail list.
	 *
	 * @return the work task detail list
	 */
	public List<WorkTaskDetail> getWorkTaskDetailList() {
		return workTaskDetailList;
	}

	/**
	 * Sets the work task detail list.
	 *
	 * @param workTaskDetailList the new work task detail list
	 */
	public void setWorkTaskDetailList(List<WorkTaskDetail> workTaskDetailList) {
		this.workTaskDetailList = workTaskDetailList;
	}

	/**
	 * Gets the work task detail.
	 *
	 * @return the work task detail
	 */
	public WorkTaskDetail getWorkTaskDetail() {
		return workTaskDetail;
	}

	/**
	 * Sets the work task detail.
	 *
	 * @param workTaskDetail the new work task detail
	 */
	public void setWorkTaskDetail(WorkTaskDetail workTaskDetail) {
		this.workTaskDetail = workTaskDetail;
	}

	/**
	 * Gets the work.
	 *
	 * @return the work
	 */
	public Work getWork() {
		return work;
	}

	/**
	 * Sets the work.
	 *
	 * @param work the new work
	 */
	public void setWork(Work work) {
		this.work = work;
	}

}
