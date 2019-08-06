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


/**
 * The Class LogicallyDeleteWorkRestVO.
 */
public class LogicallyDeleteWorkRestVO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1696464367287794062L;

	/** The id work. */
	private Long idWork;

	/**
	 * Gets the id work.
	 *
	 * @return the id work
	 */
	public Long getIdWork() {
		return idWork;
	}

	/**
	 * Sets the id work.
	 *
	 * @param idWork the new id work
	 */
	public void setIdWork(Long idWork) {
		this.idWork = idWork;
	}
}
