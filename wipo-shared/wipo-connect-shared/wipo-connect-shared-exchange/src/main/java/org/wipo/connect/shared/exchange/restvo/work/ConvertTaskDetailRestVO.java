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

import org.wipo.connect.shared.exchange.dto.impl.WorkTaskDetail;


/**
 * The Class ConvertTaskDetailRestVO.
 */
public class ConvertTaskDetailRestVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3986692536292071298L;

	/** The work task detail. */
	private WorkTaskDetail workTaskDetail;

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

}
