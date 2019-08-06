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
 * The Class RightOwnerType.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RightOwnerType", propOrder = {
    "ipin",
    "roleCode"
})
public class RightOwnerType {

    @XmlElement(required = true)
    protected String ipin;
    @XmlElement(required = true)
    protected String roleCode;

    
    /**
     * Gets the ipin.
     *
     * @return the ipin
     */
    public String getIpin() {
        return ipin;
    }

    
    /**
     * Sets the ipin.
     *
     * @param value the new ipin
     */
    public void setIpin(String value) {
        this.ipin = value;
    }

    
    /**
     * Gets the role code.
     *
     * @return the role code
     */
    public String getRoleCode() {
        return roleCode;
    }

    
    /**
     * Sets the role code.
     *
     * @param value the new role code
     */
    public void setRoleCode(String value) {
        this.roleCode = value;
    }

}
