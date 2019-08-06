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
 * The Class RulesConfigurationException.
 */
public class RulesConfigurationException extends RuntimeException {


    private static final long serialVersionUID = 7638016997775020082L;



    /**
     * Instantiates a new rules configuration exception.
     *
     * @param e
     *            the e
     */
    public RulesConfigurationException(Exception e) {
        super(e);
    }



    /**
     * Instantiates a new rules configuration exception.
     *
     * @param e
     *            the e
     */
    public RulesConfigurationException(Throwable e) {
        super(e);
    }

}
