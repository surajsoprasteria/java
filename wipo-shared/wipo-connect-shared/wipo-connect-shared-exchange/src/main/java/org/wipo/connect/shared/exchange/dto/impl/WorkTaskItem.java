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
import org.wipo.connect.common.dto.Identifiable;



/**
 * The Class WorkTaskItem.
 *
 * @author minervini
 */
public class WorkTaskItem implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7643755398314136140L;

    /** The id work task item. */
    private Long idWorkTaskItem;

    /** The fk work task. */
    private Long fkWorkTask;

    /** The fk work. */
    private Long fkWork;

    /** The item status. */
    private String itemStatus;

    /** The item code. */
    private String itemCode;

    /** The progr. */
    private Long progr;

    /** The work task detail. */
    private WorkTaskDetail workTaskDetail;

    /** The work task csi result. */
    private WorkTaskCsiResult workTaskCsiResult;

    /** The response status. */
    private String responseStatus;
    
    private String cmoCode;



    /**
     * Gets the fk work.
     *
     * @return the fk work
     */
    public Long getFkWork() {
        return this.fkWork;
    }



    /**
     * Gets the fk work task.
     *
     * @return the fk work task
     */
    public Long getFkWorkTask() {
        return this.fkWorkTask;
    }



    /**
     * Gets the id work task item.
     *
     * @return the id work task item
     */
    public Long getIdWorkTaskItem() {
        return this.idWorkTaskItem;
    }



    /**
     * Gets the item status.
     *
     * @return the item status
     */
    public String getItemStatus() {
        return this.itemStatus;
    }






    /**
     * Sets the fk work.
     *
     * @param fkWork
     *            the new fk work
     */
    public void setFkWork(Long fkWork) {
        this.fkWork = fkWork;
    }



    /**
     * Sets the fk work task.
     *
     * @param fkWorkTask
     *            the new fk work task
     */
    public void setFkWorkTask(Long fkWorkTask) {
        this.fkWorkTask = fkWorkTask;
    }



    /**
     * Sets the id work task item.
     *
     * @param idWorkTaskItem
     *            the new id work task item
     */
    public void setIdWorkTaskItem(Long idWorkTaskItem) {
        this.idWorkTaskItem = idWorkTaskItem;
    }



    /**
     * Sets the item status.
     *
     * @param itemStatus
     *            the new item status
     */
    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }






    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }



    /**
     * Gets the item code.
     *
     * @return the item code
     */
    public String getItemCode() {
        return itemCode;
    }



    /**
     * Sets the item code.
     *
     * @param itemCode the new item code
     */
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }



    /**
     * Gets the work task detail.
     *
     * @return the work task detail
     */
    public WorkTaskDetail getWorkTaskDetail() {
        return workTaskDetail;
    }



    /**
     * Sets the work task detail.
     *
     * @param workTaskDetail the new work task detail
     */
    public void setWorkTaskDetail(WorkTaskDetail workTaskDetail) {
        this.workTaskDetail = workTaskDetail;
    }



    /**
     * Gets the progr.
     *
     * @return the progr
     */
    public Long getProgr() {
        return progr;
    }



    /**
     * Sets the progr.
     *
     * @param progr the new progr
     */
    public void setProgr(Long progr) {
        this.progr = progr;
    }




    @Override
    public Long getId() {
        return getIdWorkTaskItem();
    }




    @Override
    public void setId(Long id) {
       setIdWorkTaskItem(id);
    }



    /**
     * Gets the work task csi result.
     *
     * @return the work task csi result
     */
    public WorkTaskCsiResult getWorkTaskCsiResult() {
        return workTaskCsiResult;
    }



    /**
     * Sets the work task csi result.
     *
     * @param workTaskCsiResult the new work task csi result
     */
    public void setWorkTaskCsiResult(WorkTaskCsiResult workTaskCsiResult) {
        this.workTaskCsiResult = workTaskCsiResult;
    }



    /**
     * Gets the response status.
     *
     * @return the response status
     */
    public String getResponseStatus() {
        return responseStatus;
    }



    /**
     * Sets the response status.
     *
     * @param responseStatus the new response status
     */
    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }



	public String getCmoCode() {
		return cmoCode;
	}



	public void setCmoCode(String cmoCode) {
		this.cmoCode = cmoCode;
	}
    
    
    

}
