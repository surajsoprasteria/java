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



package org.wipo.connect.ruleengine.rules.calculation;



/**
 * The Class RulesException.
 */
public class RulesException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 101310031735298816L;



    /**
     * Instantiates a new rules exception.
     */
    public RulesException() {
        super();
    }



    /**
     * Instantiates a new rules exception.
     *
     * @param message
     *            the message
     */
    public RulesException(String message) {
        super(message);
    }



    /**
     * Instantiates a new rules exception.
     *
     * @param message
     *            the message
     * @param throwable
     *            the throwable
     */
    public RulesException(String message, Throwable throwable) {
        super(message, throwable);
    }



    /**
     * Instantiates a new rules exception.
     *
     * @param throwable
     *            the throwable
     */
    public RulesException(Throwable throwable) {
        super(throwable);
    }

}
