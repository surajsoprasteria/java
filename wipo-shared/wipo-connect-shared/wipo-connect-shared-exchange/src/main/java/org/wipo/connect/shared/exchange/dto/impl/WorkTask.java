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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;



/**
 * The Class WorkTask.
 *
 * @author minervini
 */
public class WorkTask implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3553442672793370458L;

    /** The id work task. */
    private Long idWorkTask;

    /** The task code. */
    private String taskCode;
    
    /** The cmo code. */
    private String cmoCode;

    /** The work task item list. */
    private List<WorkTaskItem> workTaskItemList;



    /**
     * Gets the id work task.
     *
     * @return the id work task
     */
    public Long getIdWorkTask() {
        return this.idWorkTask;
    }



    /**
     * Gets the task code.
     *
     * @return the task code
     */
    public String getTaskCode() {
        return this.taskCode;
    }


    /**
     * Gets the work task item list.
     *
     * @return the work task item list
     */
    public List<WorkTaskItem> getWorkTaskItemList() {
        if (null == this.workTaskItemList) {
            this.workTaskItemList = new ArrayList<WorkTaskItem>();
        }
        return this.workTaskItemList;
    }



    
    



    /**
     * Sets the id work task.
     *
     * @param idWorkTask
     *            the new id work task
     */
    public void setIdWorkTask(Long idWorkTask) {
        this.idWorkTask = idWorkTask;
    }



    /**
     * Sets the task code.
     *
     * @param taskCode
     *            the new task code
     */
    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }



    /**
     * Sets the work task item list.
     *
     * @param workTaskItemList
     *            the new work task item list
     */
    public void setWorkTaskItemList(List<WorkTaskItem> workTaskItemList) {
        this.workTaskItemList = workTaskItemList;
    }




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }



	public String getCmoCode() {
		return cmoCode;
	}



	public void setCmoCode(String cmoCode) {
		this.cmoCode = cmoCode;
	}



    
    
}
