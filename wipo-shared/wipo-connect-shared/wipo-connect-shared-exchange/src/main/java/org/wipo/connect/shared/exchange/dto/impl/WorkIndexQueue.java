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
 * The Class WorkIndexQueue.
 */
public class WorkIndexQueue implements Identifiable, Serializable{

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2411473772575344695L;

    /** The id w index queue. */
    private Long idWIndexQueue;
    
    /** The fk work. */
    private Long fkWork;
    
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
        return idWIndexQueue;
    }

    /**
     * Sets the id ip index queue.
     *
     * @param idIpIndexQueue the new id ip index queue
     */
    public void setIdIpIndexQueue(Long idIpIndexQueue) {
        this.idWIndexQueue = idIpIndexQueue;
    }

    /**
     * Gets the fk work.
     *
     * @return the fk work
     */
    public Long getFkWork() {
        return fkWork;
    }

    /**
     * Sets the fk work.
     *
     * @param fkWork the new fk work
     */
    public void setFkWork(Long fkWork) {
        this.fkWork = fkWork;
    }

	/**
	 * Gets the id w index queue.
	 *
	 * @return the id w index queue
	 */
	public Long getIdWIndexQueue() {
		return idWIndexQueue;
	}

	/**
	 * Sets the id w index queue.
	 *
	 * @param idWIndexQueue the new id w index queue
	 */
	public void setIdWIndexQueue(Long idWIndexQueue) {
		this.idWIndexQueue = idWIndexQueue;
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
