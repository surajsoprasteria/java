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



package org.wipo.connect.common.dto;



import java.util.Date;



/**
 * The Interface Updatable.
 *
 * @author Minervini
 */
public interface Updatable {

    /**
     * Gets the date update.
     *
     * @return the date update
     */
    public Date getDateUpdate();



    /**
     * Gets the user update.
     *
     * @return the user update
     */
    public String getUserUpdate();



    /**
     * Sets the date update.
     *
     * @param dateUpdate
     *            the new date update
     */
    public void setDateUpdate(Date dateUpdate);



    /**
     * Sets the user update.
     *
     * @param userUpdate
     *            the new user update
     */
    public void setUserUpdate(String userUpdate);

}
