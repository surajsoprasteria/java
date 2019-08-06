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
 * The Class AffiliationDomainType.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AffiliationDomainType", propOrder = {
    "ipiRightTypeCode",
    "creationClassCode",
    "ipiRoleCode",
    "isExcluded"
})
public class AffiliationDomainType {

    @XmlElement(required = true)
    protected String ipiRightTypeCode;
    @XmlElement(required = true)
    protected String creationClassCode;
    @XmlElement(required = true)
    protected String ipiRoleCode;
    protected boolean isExcluded;

    
    /**
     * Gets the ipi right type code.
     *
     * @return the ipi right type code
     */
    public String getIpiRightTypeCode() {
        return ipiRightTypeCode;
    }

    
    /**
     * Sets the ipi right type code.
     *
     * @param value the new ipi right type code
     */
    public void setIpiRightTypeCode(String value) {
        this.ipiRightTypeCode = value;
    }

    
    /**
     * Gets the creation class code.
     *
     * @return the creation class code
     */
    public String getCreationClassCode() {
        return creationClassCode;
    }

    
    /**
     * Sets the creation class code.
     *
     * @param value the new creation class code
     */
    public void setCreationClassCode(String value) {
        this.creationClassCode = value;
    }

    
    /**
     * Gets the ipi role code.
     *
     * @return the ipi role code
     */
    public String getIpiRoleCode() {
        return ipiRoleCode;
    }

    
    /**
     * Sets the ipi role code.
     *
     * @param value the new ipi role code
     */
    public void setIpiRoleCode(String value) {
        this.ipiRoleCode = value;
    }

    
    /**
     * Checks if is checks if is excluded.
     *
     * @return true, if is checks if is excluded
     */
    public boolean isIsExcluded() {
        return isExcluded;
    }

    
    /**
     * Sets the checks if is excluded.
     *
     * @param value the new checks if is excluded
     */
    public void setIsExcluded(boolean value) {
        this.isExcluded = value;
    }

}
