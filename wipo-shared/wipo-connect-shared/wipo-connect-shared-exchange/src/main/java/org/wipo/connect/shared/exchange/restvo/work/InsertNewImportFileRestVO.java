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

import org.wipo.connect.shared.exchange.dto.impl.WorkImport;


/**
 * The Class InsertNewImportFileRestVO.
 */
public class InsertNewImportFileRestVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1014535784855264297L;

	/** The work import. */
	private WorkImport workImport;

	/**
	 * Gets the work import.
	 *
	 * @return the work import
	 */
	public WorkImport getWorkImport() {
		return workImport;
	}

	/**
	 * Sets the work import.
	 *
	 * @param workImport the new work import
	 */
	public void setWorkImport(WorkImport workImport) {
		this.workImport = workImport;
	}


}
