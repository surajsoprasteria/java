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
 * The Class ScriptConfigurationException.
 */
public class ScriptConfigurationException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 9120599850042836369L;



    /**
     * Instantiates a new script configuration exception.
     *
     * @param e
     *            the e
     */
    public ScriptConfigurationException(Exception e) {
        super(e);
    }



    /**
     * Instantiates a new script configuration exception.
     *
     * @param e
     *            the e
     */
    public ScriptConfigurationException(Throwable e) {
        super(e);
    }

}
