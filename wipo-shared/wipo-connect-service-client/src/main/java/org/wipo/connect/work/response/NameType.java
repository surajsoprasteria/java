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

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import org.wipo.connect.common.utils.XmlDateAdapter;



/**
 * The Class NameType.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NameType", propOrder = {
    "idName",
    "mainId",
    "name",
    "firstName",
    "nameType",
    "creationTimestamp",
    "amendmentTimestemp"
})
public class NameType {

    protected long idName;
    @XmlElement(required = true)
    protected String mainId;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String nameType;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    @XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
    protected Date creationTimestamp;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    @XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
    protected Date amendmentTimestemp;

    
    /**
     * Gets the id name.
     *
     * @return the id name
     */
    public long getIdName() {
        return idName;
    }

    
    /**
     * Sets the id name.
     *
     * @param value the new id name
     */
    public void setIdName(long value) {
        this.idName = value;
    }

    
    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    
    /**
     * Sets the name.
     *
     * @param value the new name
     */
    public void setName(String value) {
        this.name = value;
    }

    
    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    
    /**
     * Sets the first name.
     *
     * @param value the new first name
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    
    /**
     * Gets the name type.
     *
     * @return the name type
     */
    public String getNameType() {
        return nameType;
    }

    
    /**
     * Sets the name type.
     *
     * @param value the new name type
     */
    public void setNameType(String value) {
        this.nameType = value;
    }

    
    /**
     * Gets the creation timestamp.
     *
     * @return the creation timestamp
     */
    public Date getCreationTimestamp() {
        return creationTimestamp;
    }

    
    /**
     * Sets the creation timestamp.
     *
     * @param value the new creation timestamp
     */
    public void setCreationTimestamp(Date value) {
        this.creationTimestamp = value;
    }

    
    /**
     * Gets the amendment timestemp.
     *
     * @return the amendment timestemp
     */
    public Date getAmendmentTimestemp() {
        return amendmentTimestemp;
    }

    
    /**
     * Sets the amendment timestemp.
     *
     * @param value the new amendment timestemp
     */
    public void setAmendmentTimestemp(Date value) {
        this.amendmentTimestemp = value;
    }


	/**
	 * Gets the main id.
	 *
	 * @return the main id
	 */
	public String getMainId() {
		return mainId;
	}


	/**
	 * Sets the main id.
	 *
	 * @param mainId the new main id
	 */
	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

}
