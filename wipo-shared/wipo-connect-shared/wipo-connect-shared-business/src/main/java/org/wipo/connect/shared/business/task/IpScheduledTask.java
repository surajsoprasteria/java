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
package org.wipo.connect.shared.business.task;

import org.wipo.connect.common.exception.WccException;

/**
 * The Interface IpScheduledTask.
 */
public interface IpScheduledTask {

	/**
	 * Process import CSV.
	 *
	 * @throws Exception the exception
	 */
	void processImport() throws Exception;

	/**
	 * Search files to import.
	 *
	 * @throws WccException the wcc exception
	 */
	void searchFilesToImport() throws WccException;

	void submitIp() throws WccException;

	void processExternalResults() throws WccException;



}