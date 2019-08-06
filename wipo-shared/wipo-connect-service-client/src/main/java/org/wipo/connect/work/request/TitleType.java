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
 * The Class TitleType.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TitleType", propOrder = {
    "description",
    "typeCode"
})
public class TitleType {

    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected String typeCode;

    
    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    
    /**
     * Sets the description.
     *
     * @param value the new description
     */
    public void setDescription(String value) {
        this.description = value;
    }

    
    /**
     * Gets the type code.
     *
     * @return the type code
     */
    public String getTypeCode() {
        return typeCode;
    }

    
    /**
     * Sets the type code.
     *
     * @param value the new type code
     */
    public void setTypeCode(String value) {
        this.typeCode = value;
    }

}
