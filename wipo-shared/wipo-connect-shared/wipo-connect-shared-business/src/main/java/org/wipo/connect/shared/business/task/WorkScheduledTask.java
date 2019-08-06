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
 * Interface that exposes methods to synchronize work from local to shared.
 * Synchronization process is scheduled.
 *
 * @author minervini
 *
 */
public interface WorkScheduledTask {

     /**
      * Submit work to CISNET.
      *
      * @throws WccException the wcc exception
      */
     void submitWorkToCISNET() throws WccException;

     /**
      * Search files to import.
      *
      * @throws WccException the wcc exception
      */
     void searchFilesToImport() throws WccException;

     /**
      * Process CSI results.
      *
      * @throws WccException the wcc exception
      */
     void processCSIResults() throws WccException;
     
     /**
      * Process import CSV.
      *
      * @throws WccException the wcc exception
      * @throws Exception the exception
      */
	void processImport() throws Exception;

}