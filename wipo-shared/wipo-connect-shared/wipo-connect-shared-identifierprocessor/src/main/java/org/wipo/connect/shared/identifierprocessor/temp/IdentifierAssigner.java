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
package org.wipo.connect.shared.identifierprocessor.temp;

import org.wipo.connect.common.exception.WccException;

/**
 * The Interface IpScheduledTask.
 */
public interface IdentifierAssigner {
	
	
	void searchFilesToImport() throws WccException;
	/*

	*//**
	 * Process import CSV.
	 *
	 * @throws Exception the exception
	 *//*
	void processImport() throws Exception;

	*//**
	 * Search files to import.
	 *
	 * @throws WccException the wcc exception
	 *//*
	

	void submitIp() throws Exception;

	void processExternalResults() throws Exception;



*/}