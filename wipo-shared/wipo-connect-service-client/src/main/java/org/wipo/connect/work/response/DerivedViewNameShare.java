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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * The Class DerivedViewNameShare.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DerivedViewNameShare", propOrder = {
    "rightTypeCode",
    "shareValue",
    "cmoList"
})
public class DerivedViewNameShare {

    @XmlElement(required = true)
    protected String rightTypeCode;
    @XmlElement(required = true)
    protected BigDecimal shareValue;
    @XmlElement(required = true)
    protected List<Cmo> cmoList;

    
    /**
     * Gets the right type code.
     *
     * @return the right type code
     */
    public String getRightTypeCode() {
        return rightTypeCode;
    }

    
    /**
     * Sets the right type code.
     *
     * @param value the new right type code
     */
    public void setRightTypeCode(String value) {
        this.rightTypeCode = value;
    }

    
    /**
     * Gets the share value.
     *
     * @return the share value
     */
    public BigDecimal getShareValue() {
        return shareValue;
    }

    
    /**
     * Sets the share value.
     *
     * @param value the new share value
     */
    public void setShareValue(BigDecimal value) {
        this.shareValue = value;
    }

    /**
     * Gets the cmo list.
     *
     * @return the cmo list
     */
    public List<Cmo> getCmoList() {
        if (cmoList == null) {
        	cmoList = new ArrayList<>();
        }
        return this.cmoList;
    }
}
