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

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.wipo.connect.common.input.ContextType;



/**
 * The Class IRWorkElaborationRequest.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "context",
    "itemCodeList"
})
@XmlRootElement(name = "IRWorkElaborationRequest")
public class IRWorkElaborationRequest {

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

    
    /**
     * Gets the item code list.
     *
     * @return the item code list
     */
    public List<String> getItemCodeList() {
        if (itemCodeList == null) {
            itemCodeList = new ArrayList<>();
        }
        return this.itemCodeList;
    }

}
