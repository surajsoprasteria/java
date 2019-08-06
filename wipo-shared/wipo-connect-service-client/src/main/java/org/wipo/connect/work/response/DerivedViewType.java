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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import org.wipo.connect.common.utils.XmlDateAdapter;



/**
 * The Class DerivedViewType.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DerivedViewType", propOrder = {
    "startDate",
    "endDate",
    "territoryFormula",
    "derivedViewNameList"
})
public class DerivedViewType {

    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
    protected Date startDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
    protected Date endDate;
    @XmlElement(required = true)
    protected String territoryFormula;
    @XmlElement(required = true)
    protected List<DerivedViewNameList> derivedViewNameList;

    
    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    
    /**
     * Sets the start date.
     *
     * @param value the new start date
     */
    public void setStartDate(Date value) {
        this.startDate = value;
    }

    
    /**
     * Gets the end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    
    /**
     * Sets the end date.
     *
     * @param value the new end date
     */
    public void setEndDate(Date value) {
        this.endDate = value;
    }

    
    /**
     * Gets the territory formula.
     *
     * @return the territory formula
     */
    public String getTerritoryFormula() {
        return territoryFormula;
    }

    
    /**
     * Sets the territory formula.
     *
     * @param value the new territory formula
     */
    public void setTerritoryFormula(String value) {
        this.territoryFormula = value;
    }

    
    /**
     * Gets the derived view name list.
     *
     * @return the derived view name list
     */
    public List<DerivedViewNameList> getDerivedViewNameList() {
        if (derivedViewNameList == null) {
            derivedViewNameList = new ArrayList<DerivedViewNameList>();
        }
        return this.derivedViewNameList;
    }

}
