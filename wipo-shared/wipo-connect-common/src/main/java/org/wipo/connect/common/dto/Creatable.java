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
 * The Interface Creatable.
 *
 * @author Minervini
 */
public interface Creatable {

    /**
     * Gets the date insert.
     *
     * @return the date insert
     */
    public Date getDateInsert();



    /**
     * Gets the user insert.
     *
     * @return the user insert
     */
    public String getUserInsert();



    /**
     * Sets the date insert.
     *
     * @param dateInsert
     *            the new date insert
     */
    public void setDateInsert(Date dateInsert);



    /**
     * Sets the user insert.
     *
     * @param userInsert
     *            the new user insert
     */
    public void setUserInsert(String userInsert);

}
