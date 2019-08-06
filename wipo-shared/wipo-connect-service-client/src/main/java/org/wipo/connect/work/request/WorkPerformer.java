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
import javax.xml.bind.annotation.XmlType;



/**
 * The Class WorkPerformer.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WorkPerformer", propOrder = {
    "performerName"
})
public class WorkPerformer {

    @XmlElement(required = true)
    protected String performerName;

    
    /**
     * Gets the performer name.
     *
     * @return the performer name
     */
    public String getPerformerName() {
        return performerName;
    }

    
    /**
     * Sets the performer name.
     *
     * @param value the new performer name
     */
    public void setPerformerName(String value) {
        this.performerName = value;
    }

}
