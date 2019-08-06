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



package org.wipo.connect.shared.exchange.restvo.interestedParty;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.wipo.connect.shared.exchange.dto.impl.IpTaskItem;



/**
 * The Class StoreTaskItemDetailsRestVO.
 */
public class StoreTaskItemDetailsRestVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7561154206425489726L;

    /** The ip task item detail list. */
    private  List<IpTaskItem> ipTaskItemList;

    /** The task code. */
    private String taskCode;

    private String cmoCode;



    public List<IpTaskItem> getIpTaskItemList() {
    	if(ipTaskItemList==null){
    		ipTaskItemList=new ArrayList<>();
    	}
		return ipTaskItemList;
	}

	public void setIpTaskItemList(List<IpTaskItem> ipTaskItemList) {
		this.ipTaskItemList = ipTaskItemList;
	}

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


}
