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

import org.wipo.connect.shared.exchange.dto.impl.Work;

/**
 * The Class UpdateRestVO.
 */
public class UpdateRestVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2997419246617326826L;

	/** The work. */
	private Work work;

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
