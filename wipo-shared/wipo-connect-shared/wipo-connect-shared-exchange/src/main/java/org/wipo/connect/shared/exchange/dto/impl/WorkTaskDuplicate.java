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



package org.wipo.connect.shared.exchange.dto.impl;



import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;



/**
 * The Class WorkTaskDuplicate.
 *
 * @author minervini
 */
public class WorkTaskDuplicate implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1862036747435927439L;

    /** The id work task duplicate. */
    private Long idWorkTaskDuplicate;

    /** The fk work task item. */
    private Long fkWorkTaskItem;

    /** The progr. */
    private Long progr;

    /** The elaboration status. */
    private String elaborationStatus;

    /** The isw number. */
    private String iswNumber;



    /**
     * Gets the elaboration status.
     *
     * @return the elaboration status
     */
    public String getElaborationStatus() {
        return this.elaborationStatus;
    }



    /**
     * Gets the fk work task item.
     *
     * @return the fk work task item
     */
    public Long getFkWorkTaskItem() {
        return this.fkWorkTaskItem;
    }



    /**
     * Gets the id work task duplicate.
     *
     * @return the id work task duplicate
     */
    public Long getIdWorkTaskDuplicate() {
        return this.idWorkTaskDuplicate;
    }



    /**
     * Gets the isw number.
     *
     * @return the isw number
     */
    public String getIswNumber() {
        return this.iswNumber;
    }



    /**
     * Gets the progr.
     *
     * @return the progr
     */
    public Long getProgr() {
        return this.progr;
    }



    /**
     * Sets the elaboration status.
     *
     * @param elaborationStatus
     *            the new elaboration status
     */
    public void setElaborationStatus(String elaborationStatus) {
        this.elaborationStatus = elaborationStatus;
    }



    /**
     * Sets the fk work task item.
     *
     * @param fkWorkTaskItem
     *            the new fk work task item
     */
    public void setFkWorkTaskItem(Long fkWorkTaskItem) {
        this.fkWorkTaskItem = fkWorkTaskItem;
    }



    /**
     * Sets the id work task duplicate.
     *
     * @param idWorkTaskDuplicate
     *            the new id work task duplicate
     */
    public void setIdWorkTaskDuplicate(Long idWorkTaskDuplicate) {
        this.idWorkTaskDuplicate = idWorkTaskDuplicate;
    }



    /**
     * Sets the isw number.
     *
     * @param iswNumber
     *            the new isw number
     */
    public void setIswNumber(String iswNumber) {
        this.iswNumber = iswNumber;
    }



    /**
     * Sets the progr.
     *
     * @param progr
     *            the new progr
     */
    public void setProgr(Long progr) {
        this.progr = progr;
    }




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
