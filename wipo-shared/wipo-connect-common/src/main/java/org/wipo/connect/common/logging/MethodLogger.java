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



import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.wipo.connect.common.logging.WipoLoggerFactory;
import org.slf4j.helpers.MessageFormatter;

import ch.qos.logback.classic.Level;



/**
 * The Class MethodLogger.
 *
 * @author Minervini
 */
@Aspect
public class MethodLogger {

    private static final String START_METHOD_PATTERN = "START - Method = {} - Parameters = {} ";
    private static final String END_METHOD_PATTERN = "END - Method - Return object = {} ";
    private Logger logger;



    /**
     * Employee around advice.
     *
     * @param proceedingJoinPoint the proceeding join point
     * @return the object
     * @throws Throwable the throwable
     */
    @Around("@annotation(org.wipo.connect.common.logging.Loggable)")
    public Object methodLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

    	Class<?> clazz = proceedingJoinPoint.getClass();
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();
        String levelStr = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i] != null && args[i] instanceof Level) {
                levelStr = args[i].toString();
            }
        }

        Level level = (levelStr == null) ? Level.TRACE : Level.valueOf(levelStr);

        logStartMethod(level, clazz, methodName, Arrays.asList(args));
        Object value = null;

        value = proceedingJoinPoint.proceed();

        logEndMethod(level, clazz, value);
        return value;
    }



    private void logDebug(String message) {
        this.logger.debug(message);
    }



    /**
     * Log end method.
     *
     * @param level the level
     * @param clazz the clazz
     * @param returnValue the return value
     */
    public void logEndMethod(Level level, Class<?> clazz, Object returnValue) {
        this.logger = WipoLoggerFactory.getLogger(clazz);
        String messageLog = MessageFormatter.format(END_METHOD_PATTERN, returnValue).getMessage();

        if (level.equals(Level.TRACE)) {
            logTrace(messageLog);
        } else if (level.equals(Level.DEBUG)) {
            logDebug(messageLog);
        } else if (level.equals(Level.INFO)) {
            logInfo(messageLog);
        } else if (level.equals(Level.WARN)) {
            logWarn(messageLog);
        } else if (level.equals(Level.ERROR)) {
            logError(messageLog);
        } else {
            this.logger.warn("Invalid log level", level);
        }

    }



    private void logError(String message) {
        this.logger.error(message);
    }



    private void logInfo(String message) {
        this.logger.info(message);
    }



    /**
     * Log start method.
     *
     * @param level the level
     * @param clazz the clazz
     * @param method the method
     * @param parameters the parameters
     */
    public void logStartMethod(Level level, Class<?> clazz, Object method,
            Object... parameters) {
        this.logger = WipoLoggerFactory.getLogger(clazz);
        String parameterString = StringUtils.join(parameters, " - ");
        String messageLog = MessageFormatter.format(START_METHOD_PATTERN,
                method, parameterString).getMessage();

        if (level.equals(Level.TRACE)) {
            logTrace(messageLog);
        } else if (level.equals(Level.DEBUG)) {
            logDebug(messageLog);
        } else if (level.equals(Level.INFO)) {
            logInfo(messageLog);
        } else if (level.equals(Level.WARN)) {
            logWarn(messageLog);
        } else if (level.equals(Level.ERROR)) {
            logError(messageLog);
        } else {
            this.logger.warn("Invalid log level", level);
        }

    }



    private void logTrace(String message) {
        this.logger.trace(message);
    }



    private void logWarn(String message) {
        this.logger.warn(message);
    }

}
