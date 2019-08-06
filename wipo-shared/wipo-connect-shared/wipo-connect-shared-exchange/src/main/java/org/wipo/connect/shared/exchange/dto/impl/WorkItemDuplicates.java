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



/**
 * The Class WorkItemDuplicates.
 */
public class WorkItemDuplicates implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2538696328360092328L;

    /** The progr. */
    private Long progr;

    /** The task duplicate. */
    private WorkTaskDuplicate taskDuplicate;



    /**
     * Gets the progr.
     *
     * @return the progr
     */
    public Long getProgr() {
        return this.progr;
    }



    /**
     * Gets the task duplicate.
     *
     * @return the task duplicate
     */
    public WorkTaskDuplicate getTaskDuplicate() {
        return this.taskDuplicate;
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



    /**
     * Sets the task duplicate.
     *
     * @param taskDuplicate
     *            the new task duplicate
     */
    public void setTaskDuplicate(WorkTaskDuplicate taskDuplicate) {
        this.taskDuplicate = taskDuplicate;
    }

}
