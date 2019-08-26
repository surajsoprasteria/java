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



package org.wipo.connect.shared.identifierprocessor.temp;



import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;



/**
 * The Class BaseDTO.
 */
public abstract class BaseDTO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6920923352904207575L;

    /** The user insert. */
    private String userInsert;

    /** The date insert. */
    private Date dateInsert;

    /** The user update. */
    private String userUpdate;

    /** The date update. */
    private Date dateUpdate;



    /**
     * Gets the date insert.
     *
     * @return the date insert
     */
    public Date getDateInsert() {
        return this.dateInsert;
    }



    /**
     * Gets the date update.
     *
     * @return the date update
     */
    public Date getDateUpdate() {
        return this.dateUpdate;
    }



    /**
     * Gets the user insert.
     *
     * @return the user insert
     */
    public String getUserInsert() {
        return this.userInsert;
    }



    /**
     * Gets the user update.
     *
     * @return the user update
     */
    public String getUserUpdate() {
        return this.userUpdate;
    }



    /**
     * Sets the date insert.
     *
     * @param dateInsert
     *            the new date insert
     */
    public void setDateInsert(Date dateInsert) {
        this.dateInsert = dateInsert;
    }



    /**
     * Sets the date update.
     *
     * @param dateUpdate
     *            the new date update
     */
    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }



    /**
     * Sets the user insert.
     *
     * @param userInsert
     *            the new user insert
     */
    public void setUserInsert(String userInsert) {
        this.userInsert = userInsert;
    }



    /**
     * Sets the user update.
     *
     * @param userUpdate
     *            the new user update
     */
    public void setUserUpdate(String userUpdate) {
        this.userUpdate = userUpdate;
    }




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
