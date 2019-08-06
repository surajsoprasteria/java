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



package org.wipo.connect.ruleengine;



import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;



/**
 * The Class RejectionHandler.
 */
public class RejectionHandler implements RejectedExecutionHandler {

    /** The logger. */
    private static final Logger LOGGER = WipoLoggerFactory.getLogger(RejectionHandler.class);



    @Override
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(runnable.toString() + " : Rejected");
        }

    }

}
