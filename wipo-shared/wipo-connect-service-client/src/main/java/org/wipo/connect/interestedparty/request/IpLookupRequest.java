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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

import org.wipo.connect.common.input.ContextType;
import org.wipo.connect.common.utils.XmlDateAdapter;

/**
 * The Class IpLookupRequest.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "context", "interestedPartyRequest" })
@XmlRootElement(name = "IpLookupRequest")
public class IpLookupRequest {

    @XmlElement(required = true)
    protected ContextType context;
    @XmlElement(required = true)
    protected IpLookupRequest.InterestedPartyRequest interestedPartyRequest;

    /**
     * Gets the context.
     *
     * @return the context
     */
    public ContextType getContext() {
	return context;
    }

    /**
     * Sets the context.
     *
     * @param value
     *            the new context
     */
    public void setContext(ContextType value) {
	this.context = value;
    }

    /**
     * Gets the interested party request.
     *
     * @return the interested party request
     */
    public IpLookupRequest.InterestedPartyRequest getInterestedPartyRequest() {
	return interestedPartyRequest;
    }

    /**
     * Sets the interested party request.
     *
     * @param value
     *            the new interested party request
     */
    public void setInterestedPartyRequest(IpLookupRequest.InterestedPartyRequest value) {
	this.interestedPartyRequest = value;
    }

    /**
     * The Class InterestedPartyRequest.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "identifier", "onlyMainId", "statusCode", "birthFoundationDate", "gender", "type", "birthPlace", "birthCountryCode", "citizenship", "lastCompanyName",
	    "firstName", "nameType", "cmoAcronym", "dateFrom", "dateTo", "creationClassCodeList", "fullText", "wipoLocalId", "nameMainIdList", "nameTypeExcludeList", "paginationStart",
	    "paginationLength", "paginationDraw", "orderByExpression", "idToExcludeList", "isSimpleSearch" })
    public static class InterestedPartyRequest {

	@XmlElement(required = true)
	protected String identifier;
	@XmlElement(required = true)
	protected Boolean onlyMainId;
	@XmlElement(required = true)
	protected String statusCode;
	@XmlElement(required = true)
	@XmlSchemaType(name = "date")
	@XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
	protected Date birthFoundationDate;
	@XmlElement(required = true)
	protected String gender;
	@XmlElement(required = true)
	protected String type;
	@XmlElement(required = true)
	protected String birthPlace;
	@XmlElement(required = true)
	protected String birthCountryCode;
	@XmlElement(required = true)
	protected String citizenship;
	@XmlElement(required = true)
	protected String lastCompanyName;
	@XmlElement(required = true)
	protected String firstName;
	@XmlElement(required = true)
	protected String nameType;
	@XmlElement(required = true)
	protected String cmoAcronym;
	@XmlElement(required = true)
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
	protected Date dateFrom;
	@XmlElement(required = true)
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
	protected Date dateTo;
	@XmlElement(required = true)
	protected String fullText;
	@XmlElement(nillable = true)
	protected List<String> creationClassCodeList;
	@XmlElement(required = true)
	String wipoLocalId;
	List<String> nameMainIdList;
	List<String> nameTypeExcludeList;
	protected Integer paginationStart;
	protected Integer paginationLength;
	protected Integer paginationDraw;
	protected String orderByExpression;
	protected List<Long> idToExcludeList;
	protected Boolean isSimpleSearch;

	public Boolean getIsSimpleSearch() {
	    return isSimpleSearch;
	}

	public void setIsSimpleSearch(Boolean isSimpleSearch) {
	    this.isSimpleSearch = isSimpleSearch;
	}

	public List<String> getNameTypeExcludeList() {
	    return nameTypeExcludeList;
	}

	public void setNameTypeExcludeList(List<String> nameTypeExcludeList) {
	    this.nameTypeExcludeList = nameTypeExcludeList;
	}

	public String getWipoLocalId() {
	    return wipoLocalId;
	}

	public void setWipoLocalId(String wipoLocalId) {
	    this.wipoLocalId = wipoLocalId;
	}

	public List<String> getNameMainIdList() {
	    return nameMainIdList;
	}

	public void setNameMainIdList(List<String> nameMainIdList) {
	    this.nameMainIdList = nameMainIdList;
	}

	/**
	 * Gets the identifier.
	 *
	 * @return the identifier
	 */
	public String getIdentifier() {
	    return identifier;
	}

	/**
	 * Sets the identifier.
	 *
	 * @param value
	 *            the new identifier
	 */
	public void setIdentifier(String value) {
	    this.identifier = value;
	}

	/**
	 * Gets the status code.
	 *
	 * @return the status code
	 */
	public String getStatusCode() {
	    return statusCode;
	}

	/**
	 * Sets the status code.
	 *
	 * @param value
	 *            the new status code
	 */
	public void setStatusCode(String value) {
	    this.statusCode = value;
	}

	/**
	 * Gets the birth foundation date.
	 *
	 * @return the birth foundation date
	 */
	public Date getBirthFoundationDate() {
	    return birthFoundationDate;
	}

	/**
	 * Sets the birth foundation date.
	 *
	 * @param value
	 *            the new birth foundation date
	 */
	public void setBirthFoundationDate(Date value) {
	    this.birthFoundationDate = value;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
	    return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param value
	 *            the new gender
	 */
	public void setGender(String value) {
	    this.gender = value;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
	    return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param value
	 *            the new type
	 */
	public void setType(String value) {
	    this.type = value;
	}

	/**
	 * Gets the birth place.
	 *
	 * @return the birth place
	 */
	public String getBirthPlace() {
	    return birthPlace;
	}

	/**
	 * Sets the birth place.
	 *
	 * @param value
	 *            the new birth place
	 */
	public void setBirthPlace(String value) {
	    this.birthPlace = value;
	}

	/**
	 * Gets the birth country code.
	 *
	 * @return the birth country code
	 */
	public String getBirthCountryCode() {
	    return birthCountryCode;
	}

	/**
	 * Sets the birth country code.
	 *
	 * @param value
	 *            the new birth country code
	 */
	public void setBirthCountryCode(String value) {
	    this.birthCountryCode = value;
	}

	/**
	 * Gets the citizenship.
	 *
	 * @return the citizenship
	 */
	public String getCitizenship() {
	    return citizenship;
	}

	/**
	 * Sets the citizenship.
	 *
	 * @param value
	 *            the new citizenship
	 */
	public void setCitizenship(String value) {
	    this.citizenship = value;
	}

	/**
	 * Gets the last company name.
	 *
	 * @return the last company name
	 */
	public String getLastCompanyName() {
	    return lastCompanyName;
	}

	/**
	 * Sets the last company name.
	 *
	 * @param value
	 *            the new last company name
	 */
	public void setLastCompanyName(String value) {
	    this.lastCompanyName = value;
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
	 * @param value
	 *            the new first name
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
	 * @param value
	 *            the new name type
	 */
	public void setNameType(String value) {
	    this.nameType = value;
	}

	/**
	 * Gets the cmo acronym.
	 *
	 * @return the cmo acronym
	 */
	public String getCmoAcronym() {
	    return cmoAcronym;
	}

	/**
	 * Sets the cmo acronym.
	 *
	 * @param value
	 *            the new cmo acronym
	 */
	public void setCmoAcronym(String value) {
	    this.cmoAcronym = value;
	}

	/**
	 * Gets the date from.
	 *
	 */
	public Date getDateFrom() {
	    return dateFrom;
	}

	/**
	 * Sets the date from.
	 *
	 * @param value
	 *            the new date from
	 */
	public void setDateFrom(Date value) {
	    this.dateFrom = value;
	}

	/**
	 * Gets the date to.
	 *
	 * @return the date to
	 */
	public Date getDateTo() {
	    return dateTo;
	}

	/**
	 * Sets the date to.
	 *
	 * @param value
	 *            the new date to
	 */
	public void setDateTo(Date value) {
	    this.dateTo = value;
	}

	/**
	 * Gets the full text.
	 *
	 * @return the full text
	 */
	public String getFullText() {
	    return fullText;
	}

	/**
	 * Sets the full text.
	 *
	 * @param value
	 *            the new full text
	 */
	public void setFullText(String value) {
	    this.fullText = value;
	}

	public List<String> getCreationClassCodeList() {
	    if (creationClassCodeList == null) {
		creationClassCodeList = new ArrayList<>();
	    }
	    return this.creationClassCodeList;
	}

	public void setCreationClassCodeList(List<String> creationClassCodeList) {
	    this.creationClassCodeList = creationClassCodeList;
	}

	public Boolean getOnlyMainId() {
	    return onlyMainId;
	}

	public void setOnlyMainId(Boolean onlyMainId) {
	    this.onlyMainId = onlyMainId;
	}

	public Integer getPaginationStart() {
	    return paginationStart;
	}

	public void setPaginationStart(Integer paginationStart) {
	    this.paginationStart = paginationStart;
	}

	public Integer getPaginationLength() {
	    return paginationLength;
	}

	public void setPaginationLength(Integer paginationLength) {
	    this.paginationLength = paginationLength;
	}

	public Integer getPaginationDraw() {
	    return paginationDraw;
	}

	public void setPaginationDraw(Integer paginationDraw) {
	    this.paginationDraw = paginationDraw;
	}

	public String getOrderByExpression() {
	    return orderByExpression;
	}

	public void setOrderByExpression(String orderByExpression) {
	    this.orderByExpression = orderByExpression;
	}

	public List<Long> getIdToExcludeList() {
	    if (idToExcludeList == null) {
		idToExcludeList = new ArrayList<>();
	    }
	    return idToExcludeList;
	}

	public void setIdToExcludeList(List<Long> idToExcludeList) {
	    this.idToExcludeList = idToExcludeList;
	}

    }

}
