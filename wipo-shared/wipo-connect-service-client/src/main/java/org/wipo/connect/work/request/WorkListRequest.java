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
 * The Class WorkListRequest.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "context", "workRequest" })
@XmlRootElement(name = "WorkListRequest")
public class WorkListRequest {

    @XmlElement(required = true)
    protected ContextType context;
    @XmlElement(required = true)
    protected WorkListRequest.WorkRequest workRequest;

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
     * Gets the work request.
     *
     * @return the work request
     */
    public WorkListRequest.WorkRequest getWorkRequest() {
	return workRequest;
    }

    /**
     * Sets the work request.
     *
     * @param value
     *            the new work request
     */
    public void setWorkRequest(WorkListRequest.WorkRequest value) {
	this.workRequest = value;
    }

    /**
     * The Class WorkRequest.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
	    "identifier",
	    "onlyMainId",
	    "title",
	    "statusCode",
	    "originalTitles",
	    "sourceType",
	    "workType",
	    "registrationDate",
	    "performer",
	    "rightOwnerList",
	    "fullText",
	    "catalogueNumber",
	    "categoryCode",
	    "label",
	    "countryOfProductionCode",
	    "dateTypeCode",
	    "dateValue",
	    "creationClassCodeList",
	    "wipoLocalId",
	    "identifierSearchVOList",
	    "originalTitleTrimmed",
	    "rightOwnerListSearch",
	    "loadAllFieldFromShared",
	    "paginationStart",
	    "paginationLength",
	    "paginationDraw",
	    "orderByExpression",
	    "idToExcludeList",
	    "isSimpleSearch"
    })
    public static class WorkRequest {

	@XmlElement(required = true)
	protected String identifier;
	@XmlElement(required = true)
	protected Boolean onlyMainId;
	@XmlElement(required = true)
	protected String title;
	@XmlElement(required = true)
	protected String statusCode;
	protected boolean originalTitles;
	@XmlElement(required = true)
	protected String sourceType;
	@XmlElement(required = true)
	protected String workType;
	@XmlElement(required = true)
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
	protected Date registrationDate;
	@XmlElement(required = true)
	protected String performer;
	protected List<RightOwnerType> rightOwnerList;
	@XmlElement(required = true)
	protected String fullText;
	protected String catalogueNumber;
	protected String countryOfProductionCode;
	protected String categoryCode;
	protected String label;
	protected String dateTypeCode;
	@XmlElement(required = true)
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
	protected Date dateValue;
	protected List<String> creationClassCodeList;
	@XmlElement(required = true)
	protected String wipoLocalId;
	protected List<IdentifierType> identifierSearchVOList;
	protected String originalTitleTrimmed;
	protected List<RightOwnerVO> rightOwnerListSearch;
	protected Boolean loadAllFieldFromShared;
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

	public String getOriginalTitleTrimmed() {
	    return originalTitleTrimmed;
	}

	public void setOriginalTitleTrimmed(String originalTitleTrimmed) {
	    this.originalTitleTrimmed = originalTitleTrimmed;
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
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
	    return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param value
	 *            the new title
	 */
	public void setTitle(String value) {
	    this.title = value;
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
	 * Checks if is original titles.
	 *
	 * @return true, if is original titles
	 */
	public boolean isOriginalTitles() {
	    return originalTitles;
	}

	/**
	 * Sets the original titles.
	 *
	 * @param value
	 *            the new original titles
	 */
	public void setOriginalTitles(boolean value) {
	    this.originalTitles = value;
	}

	public String getSourceType() {
	    return sourceType;
	}

	/**
	 * Sets the source type.
	 *
	 * @param value
	 *            the new source type
	 */
	public void setSourceType(String value) {
	    this.sourceType = value;
	}

	/**
	 * Gets the work type.
	 *
	 * @return the work type
	 */
	public String getWorkType() {
	    return workType;
	}

	/**
	 * Sets the work type.
	 *
	 * @param value
	 *            the new work type
	 */
	public void setWorkType(String value) {
	    this.workType = value;
	}

	/**
	 * Gets the registration date.
	 *
	 * @return the registration date
	 */
	public Date getRegistrationDate() {
	    return registrationDate;
	}

	/**
	 * Sets the registration date.
	 *
	 * @param value
	 *            the new registration date
	 */
	public void setRegistrationDate(Date value) {
	    this.registrationDate = value;
	}

	/**
	 * Gets the performer.
	 *
	 * @return the performer
	 */
	public String getPerformer() {
	    return performer;
	}

	/**
	 * Sets the performer.
	 *
	 * @param value
	 *            the new performer
	 */
	public void setPerformer(String value) {
	    this.performer = value;
	}

	/**
	 * Gets the right owner list.
	 *
	 * @return the right owner list
	 */
	public List<RightOwnerType> getRightOwnerList() {
	    if (rightOwnerList == null) {
		rightOwnerList = new ArrayList<>();
	    }
	    return this.rightOwnerList;
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

	public String getCatalogueNumber() {
	    return catalogueNumber;
	}

	public void setCatalogueNumber(String catalogueNumber) {
	    this.catalogueNumber = catalogueNumber;
	}

	public String getCountryOfProductionCode() {
	    return countryOfProductionCode;
	}

	public void setCountryOfProductionCode(String countryOfProductionCode) {
	    this.countryOfProductionCode = countryOfProductionCode;
	}

	public String getCategoryCode() {
	    return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
	    this.categoryCode = categoryCode;
	}

	public String getLabel() {
	    return label;
	}

	public void setLabel(String label) {
	    this.label = label;
	}

	public String getDateTypeCode() {
	    return dateTypeCode;
	}

	public void setDateTypeCode(String dateTypeCode) {
	    this.dateTypeCode = dateTypeCode;
	}

	public Date getDateValue() {
	    return dateValue;
	}

	public void setDateValue(Date dateValue) {
	    this.dateValue = dateValue;
	}

	public List<String> getCreationClassCodeList() {
	    if (creationClassCodeList == null) {
		creationClassCodeList = new ArrayList<>();
	    }
	    return creationClassCodeList;
	}

	public void setCreationClassCodeList(List<String> creationClassCodeList) {
	    this.creationClassCodeList = creationClassCodeList;
	}

	public String getWipoLocalId() {
	    return wipoLocalId;
	}

	public void setWipoLocalId(String wipoLocalId) {
	    this.wipoLocalId = wipoLocalId;
	}

	public List<IdentifierType> getIdentifierSearchVOList() {
	    if (identifierSearchVOList == null) {
		identifierSearchVOList = new ArrayList<>();
	    }
	    return identifierSearchVOList;
	}

	public void setIdentifierSearchVOList(List<IdentifierType> identifierSearchVOList) {
	    this.identifierSearchVOList = identifierSearchVOList;
	}

	public List<RightOwnerVO> getRightOwnerListSearch() {
	    if (rightOwnerListSearch == null) {
		rightOwnerListSearch = new ArrayList<>();
	    }
	    return rightOwnerListSearch;
	}

	public Boolean getOnlyMainId() {
	    return onlyMainId;
	}

	public void setOnlyMainId(Boolean onlyMainId) {
	    this.onlyMainId = onlyMainId;
	}

	public Boolean getLoadAllFieldFromShared() {
	    return loadAllFieldFromShared;
	}

	public void setLoadAllFieldFromShared(Boolean loadAllFieldFromShared) {
	    this.loadAllFieldFromShared = loadAllFieldFromShared;
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
