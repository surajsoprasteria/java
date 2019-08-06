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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



/**
 * The Class IpNamesType.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IpNamesType", propOrder = {
    "name",
    "firstName",
    "nameType",
    "mainId"
})
public class IpNamesType {

    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String nameType;
    @XmlElement(required = true)
    protected String mainId;

    
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
