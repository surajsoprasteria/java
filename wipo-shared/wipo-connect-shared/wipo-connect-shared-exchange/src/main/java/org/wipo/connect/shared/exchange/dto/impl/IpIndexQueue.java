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

import org.wipo.connect.common.dto.Identifiable;


/**
 * The Class IpIndexQueue.
 */
public class IpIndexQueue implements Identifiable, Serializable{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2411473772575344695L;

    /** The id ip index queue. */
    private Long idIpIndexQueue;
    
    /** The fk interested party. */
    private Long fkInterestedParty;
    
    /** The action. */
    private String action;



    @Override
    public Long getId() {
        return getIdIpIndexQueue();
    }


    @Override
    public void setId(Long id) {
        setIdIpIndexQueue(id);
    }

    /**
     * Gets the id ip index queue.
     *
     * @return the id ip index queue
     */
    public Long getIdIpIndexQueue() {
        return idIpIndexQueue;
    }

    /**
     * Sets the id ip index queue.
     *
     * @param idIpIndexQueue the new id ip index queue
     */
    public void setIdIpIndexQueue(Long idIpIndexQueue) {
        this.idIpIndexQueue = idIpIndexQueue;
    }

    /**
     * Gets the fk interested party.
     *
     * @return the fk interested party
     */
    public Long getFkInterestedParty() {
        return fkInterestedParty;
    }

    /**
     * Sets the fk interested party.
     *
     * @param fkInterestedParty the new fk interested party
     */
    public void setFkInterestedParty(Long fkInterestedParty) {
        this.fkInterestedParty = fkInterestedParty;
    }

	/**
	 * Gets the action.
	 *
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Sets the action.
	 *
	 * @param action the new action
	 */
	public void setAction(String action) {
		this.action = action;
	}


}
