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



package org.wipo.connect.common.exception;



/**
 * The Class WccRuntimeException.
 *
 * @author fumagalli
 */
public class WccRuntimeException extends RuntimeException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6351115805836990688L;



    /**
     * Instantiates a new wcc runtime exception.
     *
     * @param message
     *            the message
     */
    public WccRuntimeException(String message) {
        super(message);
    }



    /**
     * Instantiates a new wcc runtime exception.
     *
     * @param message
     *            the message
     * @param cause
     *            the cause
     */
    public WccRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }



    /**
     * Instantiates a new wcc runtime exception.
     *
     * @param cause
     *            the cause
     */
    public WccRuntimeException(Throwable cause) {
        super(cause);
    }

}
