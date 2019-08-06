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



package org.wipo.connect.shared.exchange.restvo.work;



import java.io.Serializable;
import java.util.List;

import org.wipo.connect.shared.exchange.dto.impl.WorkTaskItem;



/**
 * The Class StoreTaskItemDetailsRestVO.
 */
public class StoreTaskItemDetailsRestVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7561154206425489726L;

    /** The task code. */
    private String taskCode;
    
    private String cmoCode;
    
    /** The item list. */
    private List<WorkTaskItem> itemList;



    /**
     * Gets the task code.
     *
     * @return the task code
     */
    public String getTaskCode() {
        return taskCode;
    }



    /**
     * Sets the task code.
     *
     * @param taskCode the new task code
     */
    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }



    public String getCmoCode() {
		return cmoCode;
	}



	public void setCmoCode(String cmoCode) {
		this.cmoCode = cmoCode;
	}



	/**
     * Gets the item list.
     *
     * @return the item list
     */
    public List<WorkTaskItem> getItemList() {
        return itemList;
    }



    /**
     * Sets the item list.
     *
     * @param itemList the new item list
     */
    public void setItemList(List<WorkTaskItem> itemList) {
        this.itemList = itemList;
    }

}
