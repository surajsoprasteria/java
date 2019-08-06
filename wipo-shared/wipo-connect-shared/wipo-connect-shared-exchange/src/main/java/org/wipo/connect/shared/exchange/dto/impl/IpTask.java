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



import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;



/**
 * The Class IpTask.
 *
 * @author minervini
 */
public class IpTask {

    /** The id ip task. */
    private Long idIpTask;

    /** The task code. */
    private String taskCode;

    /** The cmo code. */
    private String cmoCode;

    /** The ip task item list. */
    private List<IpTaskItem> ipTaskItemList;



    /**
     * Gets the id ip task.
     *
     * @return the id ip task
     */
    public Long getIdIpTask() {
        return this.idIpTask;
    }



    /**
     * Gets the ip task item list.
     *
     * @return the ip task item list
     */
    public List<IpTaskItem> getIpTaskItemList() {
        if (this.ipTaskItemList == null) {
            this.ipTaskItemList = new ArrayList<IpTaskItem>();
        }
        return this.ipTaskItemList;
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
     * Sets the id ip task.
     *
     * @param idIpTask
     *            the new id ip task
     */
    public void setIdIpTask(Long idIpTask) {
        this.idIpTask = idIpTask;
    }



    /**
     * Sets the ip task item list.
     *
     * @param ipTaskItemList
     *            the new ip task item list
     */
    public void setIpTaskItemList(List<IpTaskItem> ipTaskItemList) {
        this.ipTaskItemList = ipTaskItemList;
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
