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



/**
 * The Class LoadDuplicateRestVO.
 */
public class LoadDuplicateRestVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7561154206425489726L;


    /** The task code. */
    private String taskCode;
   
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


}
