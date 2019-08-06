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

package org.wipo.connect.work.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.wipo.connect.common.input.ContextType;

/**
 * The Class WorkDetailRequest.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"context",
	"workRequest"
})
@XmlRootElement(name = "WorkDetailRequest")
public class WorkDetailRequest {

    @XmlElement(required = true)
    protected ContextType context;
    @XmlElement(required = true)
    protected WorkDetailRequest.WorkRequest workRequest;

    /**
     * Gets the context.
     *
     * @return the context
     */
    public ContextType getContext() {
	return context;
    }

    /**
     * Sets the context.
     *
     * @param value
     *            the new context
     */
    public void setContext(ContextType value) {
	this.context = value;
    }

    /**
     * Gets the work request.
     *
     * @return the work request
     */
    public WorkDetailRequest.WorkRequest getWorkRequest() {
	return workRequest;
    }

    /**
     * Sets the work request.
     *
     * @param value
     *            the new work request
     */
    public void setWorkRequest(WorkDetailRequest.WorkRequest value) {
	this.workRequest = value;
    }

    /**
     * The Class WorkRequest.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
	    "idWork",
	    "mainId"
    })
    public static class WorkRequest {

	protected long idWork;

	protected String mainId;

	public long getIdWork() {
	    return idWork;
	}

	public void setIdWork(long value) {
	    this.idWork = value;
	}

	public String getMainId() {
	    return mainId;
	}

	public void setMainId(String mainId) {
	    this.mainId = mainId;
	}

    }

}
