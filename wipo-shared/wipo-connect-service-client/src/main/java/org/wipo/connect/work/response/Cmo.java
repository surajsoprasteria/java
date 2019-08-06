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

package org.wipo.connect.work.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The Class Cmo.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Cmo", propOrder = {
    "code"
})
public class Cmo {

    @XmlElement(required = true)
    protected String code;

    
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

}
