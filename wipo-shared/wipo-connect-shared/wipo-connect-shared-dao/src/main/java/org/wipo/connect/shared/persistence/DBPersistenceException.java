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



package org.wipo.connect.shared.persistence;



/**
 * Purpose:
 *
 * Project Name : First created by: Minervini Creation date: 16/mag/2013.
 */
public class DBPersistenceException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7251617282993989477L;



    /**
     * Wrapper exception method.
     *
     * @param ex
     *            to manage
     */
    public DBPersistenceException(Exception ex) {
        super(ex);
    }



    /**
     * Wrapper exception method.
     *
     * @param message
     *            description
     */
    public DBPersistenceException(String message) {
        super(message);
    }



    /**
     * Wrapper exception method.
     *
     * @param message
     *            description
     * @param ex
     *            to manage
     */
    public DBPersistenceException(String message, Exception ex) {
        super(message, ex);
    }

}
