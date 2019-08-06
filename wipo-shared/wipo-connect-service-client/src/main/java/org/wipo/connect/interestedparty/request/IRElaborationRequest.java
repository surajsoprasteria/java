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

package org.wipo.connect.interestedparty.request;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.wipo.connect.common.input.ContextType;



/**
 * The Class IRElaborationRequest.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "context",
    "itemCodeList"
})
@XmlRootElement(name = "IRElaborationRequest")
public class IRElaborationRequest {

    @XmlElement(required = true)
    protected ContextType context;
    @XmlElement(required = true)
    protected List<String> itemCodeList;


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
     * @param value the new context
     */
    public void setContext(ContextType value) {
        this.context = value;
    }


	public List<String> getItemCodeList() {
		return itemCodeList;
	}


	public void setItemCodeList(List<String> itemCodeList) {
		this.itemCodeList = itemCodeList;
	}



}
