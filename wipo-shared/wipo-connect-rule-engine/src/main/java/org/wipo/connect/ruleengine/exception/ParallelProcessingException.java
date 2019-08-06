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



package org.wipo.connect.ruleengine.exception;



/**
 * The Class ParallelProcessingException.
 */
public class ParallelProcessingException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -743487369260776545L;



    /**
     * Instantiates a new parallel processing exception.
     *
     * @param message
     *            the message
     */
    public ParallelProcessingException(String message) {
        super(message);
    }

}
