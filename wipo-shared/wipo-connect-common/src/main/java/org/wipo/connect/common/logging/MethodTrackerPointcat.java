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

package org.wipo.connect.common.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;

/**
 * The Class MethodTrackerPointcat.
 *
 * @author Minervini
 */
@Aspect
public class MethodTrackerPointcat {

    private static final Logger LOGGER = WipoLoggerFactory.getLogger(MethodTrackerPointcat.class);

    @Around("@annotation(org.wipo.connect.common.logging.ExecutionTimeTrackable)")
    public Object executionTimeTracker(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
	Long startTime = System.currentTimeMillis();
	Object value = proceedingJoinPoint.proceed();
	Long executionTime = System.currentTimeMillis() - startTime;

	String methodName = proceedingJoinPoint.getSignature().toString();
	LOGGER.debug("[{}] execution time: {} ms", methodName, executionTime);
	return value;
    }
}
