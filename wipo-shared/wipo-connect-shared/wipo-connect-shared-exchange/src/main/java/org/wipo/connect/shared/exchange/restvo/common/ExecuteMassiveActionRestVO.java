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



package org.wipo.connect.shared.exchange.restvo.common;



import java.io.Serializable;
import java.util.List;

import org.wipo.connect.shared.exchange.enumerator.MassiveActionsEnum;



/**
 * The Class ExecuteMassiveActionRestVO.
 */
public class ExecuteMassiveActionRestVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2652470860399671187L;

    /** The id list. */
    private List<Long> idList;
    
    /** The action. */
    private MassiveActionsEnum action;



    /**
     * Gets the id list.
     *
     * @return the id list
     */
    public List<Long> getIdList() {
        return idList;
    }



    /**
     * Sets the id list.
     *
     * @param idList the new id list
     */
    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }


    /**
     * Gets the action.
     *
     * @return the action
     */
    public MassiveActionsEnum getAction() {
        return action;
    }



    /**
     * Sets the action.
     *
     * @param action the new action
     */
    public void setAction(MassiveActionsEnum action) {
        this.action = action;
    }

}
