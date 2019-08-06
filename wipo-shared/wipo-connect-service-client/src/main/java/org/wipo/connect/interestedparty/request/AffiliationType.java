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

import java.math.BigDecimal;
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
 * The Class AffiliationType.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AffiliationType", propOrder = {
    "cmoCode",
    "territoryFormula",
    "startDate",
    "endDate",
    "shareValue",
    "signatureDate",
    "amendmentTimestemp",
    "affiliationDomainList"
})
public class AffiliationType {

    @XmlElement(required = true)
    protected String cmoCode;
    @XmlElement(required = true)
    protected String territoryFormula;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
    protected Date startDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
    protected Date endDate;
    @XmlElement(required = true)
    protected BigDecimal shareValue;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    @XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
    protected Date signatureDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    @XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
    protected Date amendmentTimestemp;
    @XmlElement(required = true)
    protected List<AffiliationDomainType> affiliationDomainList;




    public String getCmoCode() {
		return cmoCode;
	}


	public void setCmoCode(String cmoCode) {
		this.cmoCode = cmoCode;
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
     * Gets the signature date.
     *
     * @return the signature date
     */
    public Date getSignatureDate() {
        return signatureDate;
    }


    /**
     * Sets the signature date.
     *
     * @param value the new signature date
     */
    public void setSignatureDate(Date value) {
        this.signatureDate = value;
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
     * Gets the affiliation domain list.
     *
     * @return the affiliation domain list
     */
    public List<AffiliationDomainType> getAffiliationDomainList() {
        if (affiliationDomainList == null) {
            affiliationDomainList = new ArrayList<AffiliationDomainType>();
        }
        return this.affiliationDomainList;
    }

}
