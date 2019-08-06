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
 * The Class FlatType.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FlatType", propOrder = {
    "code",
    "value"
})
public class FlatType {

    @XmlElement(required = true)
    protected String code;
    @XmlElement(required = true)
    protected String value;

    
    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    
    /**
     * Sets the code.
     *
     * @param value the new code
     */
    public void setCode(String value) {
        this.code = value;
    }

    
    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    
    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(String value) {
        this.value = value;
    }

}
