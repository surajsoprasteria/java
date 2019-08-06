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

import org.wipo.connect.common.output.ErrorType;
import org.wipo.connect.common.utils.XmlDateAdapter;

/**
 * The Class WorkDetailResponse.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "workResponse", "error" })
@XmlRootElement(name = "WorkDetailResponse")
public class WorkDetailResponse {

    @XmlElement(required = true, nillable = true)
    protected WorkDetailResponse.WorkResponse workResponse;
    @XmlElement(name = "Error")
    protected ErrorType error;

    /**
     * Gets the work response.
     *
     * @return the work response
     */
    public WorkDetailResponse.WorkResponse getWorkResponse() {
	return workResponse;
    }

    /**
     * Sets the work response.
     *
     * @param value
     *            the new work response
     */
    public void setWorkResponse(WorkDetailResponse.WorkResponse value) {
	this.workResponse = value;
    }

    /**
     * Gets the error.
     *
     * @return the error
     */
    public ErrorType getError() {
	return error;
    }

    /**
     * Sets the error.
     *
     * @param value
     *            the new error
     */
    public void setError(ErrorType value) {
	this.error = value;
    }

    /**
     * The Class WorkResponse.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "idWork", "duration", "statusCode", "genreCode", "typeCode", "domain", "releaseDate", "registrationDate", "workCreationDate", "sourceTypeCode",
	    "domesticWork", "lastUpdateDate", "excerptTypeCode", "compositeTypeCode", "workPerformerList", "derivedWorkList", "derivedWorkParentList", "instrumentCodeList",
	    "identifierList", "titleList", "derivedViewList", "mainId", "creationClassCode", "componentPerc", "catalogueNumber", "label", "countryOfProductionCode",
	    "categoryCode", "support", "language", "workDateList", "cmoOriginCode", "syncVersion"

    })
    public static class WorkResponse {

	protected long idWork;
	@XmlElement(nillable = true)
	protected Long duration;
	@XmlElement(required = true)
	protected String statusCode;
	@XmlElement(required = true, nillable = true)
	protected String genreCode;
	@XmlElement(required = true, nillable = true)
	protected String typeCode;
	@XmlElement(required = true)
	protected String domain;
	@XmlElement(required = true, nillable = true)
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
	protected Date releaseDate;
	@XmlElement(required = true, nillable = true)
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
	protected Date registrationDate;
	@XmlElement(required = true, nillable = true)
	@XmlSchemaType(name = "dateTime")
	@XmlJavaTypeAdapter(type = XMLGregorianCalendar.class, value = XmlDateAdapter.class)
	protected Date workCreationDate;
	@XmlElement(required = true, nillable = true)
	protected String sourceTypeCode;
	protected boolean domesticWork;
	@XmlElement(required = true, nillable = true)
	@XmlSchemaType(name = "dateTime")
	protected Date lastUpdateDate;
	@XmlElement(required = true, nillable = true)
	protected String excerptTypeCode;
	@XmlElement(required = true, nillable = true)
	protected String compositeTypeCode;
	protected List<WorkPerformer> workPerformerList;
	protected List<DerivedWork> derivedWorkList;
	protected List<String> instrumentCodeList;
	@XmlElement(required = true)
	protected List<FlatType> identifierList;
	@XmlElement(required = true)
	protected List<TitleType> titleList;
	protected List<DerivedViewType> derivedViewList;
	@XmlElement(required = true)
	protected String mainId;
	protected String creationClassCode;
	protected BigDecimal componentPerc;
	protected List<DerivedWork> derivedWorkParentList;
	protected String catalogueNumber;
	protected String label;
	protected String countryOfProductionCode;
	protected String categoryCode;
	protected String support;
	protected String language;
	protected List<WorkDateType> workDateList;
	protected String cmoOriginCode;
	protected Long syncVersion;

	public Long getSyncVersion() {
	    return syncVersion;
	}

	public void setSyncVersion(Long syncVersion) {
	    this.syncVersion = syncVersion;
	}

	public String getCategoryCode() {
	    return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
	    this.categoryCode = categoryCode;
	}

	public String getSupport() {
	    return support;
	}

	public void setSupport(String support) {
	    this.support = support;
	}

	public String getLanguage() {
	    return language;
	}

	public void setLanguage(String language) {
	    this.language = language;
	}

	public String getCatalogueNumber() {
	    return catalogueNumber;
	}

	public void setCatalogueNumber(String catalogueNumber) {
	    this.catalogueNumber = catalogueNumber;
	}

	public String getLabel() {
	    return label;
	}

	public void setLabel(String label) {
	    this.label = label;
	}

	public String getCountryOfProductionCode() {
	    return countryOfProductionCode;
	}

	public void setCountryOfProductionCode(String countryOfProductionCode) {
	    this.countryOfProductionCode = countryOfProductionCode;
	}

	public List<WorkDateType> getWorkDateList() {
	    return workDateList;
	}

	public void setWorkDateList(List<WorkDateType> workDateList) {
	    this.workDateList = workDateList;
	}

	/**
	 * Gets the id work.
	 *
	 * @return the id work
	 */
	public long getIdWork() {
	    return idWork;
	}

	/**
	 * Sets the id work.
	 *
	 * @param value
	 *            the new id work
	 */
	public void setIdWork(long value) {
	    this.idWork = value;
	}

	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public Long getDuration() {
	    return duration;
	}

	/**
	 * Sets the duration.
	 *
	 * @param value
	 *            the new duration
	 */
	public void setDuration(Long value) {
	    this.duration = value;
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
	 * Gets the genre code.
	 *
	 * @return the genre code
	 */
	public String getGenreCode() {
	    return genreCode;
	}

	/**
	 * Sets the genre code.
	 *
	 * @param value
	 *            the new genre code
	 */
	public void setGenreCode(String value) {
	    this.genreCode = value;
	}

	/**
	 * Gets the type code.
	 *
	 * @return the type code
	 */
	public String getTypeCode() {
	    return typeCode;
	}

	/**
	 * Sets the type code.
	 *
	 * @param value
	 *            the new type code
	 */
	public void setTypeCode(String value) {
	    this.typeCode = value;
	}

	/**
	 * Gets the domain.
	 *
	 * @return the domain
	 */
	public String getDomain() {
	    return domain;
	}

	/**
	 * Sets the domain.
	 *
	 * @param value
	 *            the new domain
	 */
	public void setDomain(String value) {
	    this.domain = value;
	}

	/**
	 * Gets the release date.
	 *
	 * @return the release date
	 */
	public Date getReleaseDate() {
	    return releaseDate;
	}

	/**
	 * Sets the release date.
	 *
	 * @param value
	 *            the new release date
	 */
	public void setReleaseDate(Date value) {
	    this.releaseDate = value;
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
	 * Gets the work creation date.
	 *
	 * @return the work creation date
	 */
	public Date getWorkCreationDate() {
	    return workCreationDate;
	}

	/**
	 * Sets the work creation date.
	 *
	 * @param value
	 *            the new work creation date
	 */
	public void setWorkCreationDate(Date value) {
	    this.workCreationDate = value;
	}

	/**
	 * Gets the source type code.
	 *
	 * @return the source type code
	 */
	public String getSourceTypeCode() {
	    return sourceTypeCode;
	}

	/**
	 * Sets the source type code.
	 *
	 * @param value
	 *            the new source type code
	 */
	public void setSourceTypeCode(String value) {
	    this.sourceTypeCode = value;
	}

	/**
	 * Checks if is domestic work.
	 *
	 * @return true, if is domestic work
	 */
	public boolean isDomesticWork() {
	    return domesticWork;
	}

	/**
	 * Sets the domestic work.
	 *
	 * @param value
	 *            the new domestic work
	 */
	public void setDomesticWork(boolean value) {
	    this.domesticWork = value;
	}

	/**
	 * Gets the last update date.
	 *
	 * @return the last update date
	 */
	public Date getLastUpdateDate() {
	    return lastUpdateDate;
	}

	/**
	 * Sets the last update date.
	 *
	 * @param value
	 *            the new last update date
	 */
	public void setLastUpdateDate(Date value) {
	    this.lastUpdateDate = value;
	}

	/**
	 * Gets the excerpt type code.
	 *
	 * @return the excerpt type code
	 */
	public String getExcerptTypeCode() {
	    return excerptTypeCode;
	}

	/**
	 * Sets the excerpt type code.
	 *
	 * @param value
	 *            the new excerpt type code
	 */
	public void setExcerptTypeCode(String value) {
	    this.excerptTypeCode = value;
	}

	/**
	 * Gets the composite type code.
	 *
	 * @return the composite type code
	 */
	public String getCompositeTypeCode() {
	    return compositeTypeCode;
	}

	/**
	 * Sets the composite type code.
	 *
	 * @param value
	 *            the new composite type code
	 */
	public void setCompositeTypeCode(String value) {
	    this.compositeTypeCode = value;
	}

	/**
	 * Gets the work performer list.
	 *
	 * @return the work performer list
	 */
	public List<WorkPerformer> getWorkPerformerList() {
	    if (workPerformerList == null) {
		workPerformerList = new ArrayList<>();
	    }
	    return this.workPerformerList;
	}

	/**
	 * Gets the derived work list.
	 *
	 * @return the derived work list
	 */
	public List<DerivedWork> getDerivedWorkList() {
	    if (derivedWorkList == null) {
		derivedWorkList = new ArrayList<>();
	    }
	    return this.derivedWorkList;
	}

	/**
	 * Gets the instrument code list.
	 *
	 * @return the instrument code list
	 */
	public List<String> getInstrumentCodeList() {
	    if (instrumentCodeList == null) {
		instrumentCodeList = new ArrayList<>();
	    }
	    return this.instrumentCodeList;
	}

	public void setInstrumentCodeList(List<String> instrumentCodeList) {
	    this.instrumentCodeList = instrumentCodeList;
	}

	/**
	 * Gets the identifier list.
	 *
	 * @return the identifier list
	 */
	public List<FlatType> getIdentifierList() {
	    if (identifierList == null) {
		identifierList = new ArrayList<>();
	    }
	    return this.identifierList;
	}

	/**
	 * Gets the title list.
	 *
	 * @return the title list
	 */
	public List<TitleType> getTitleList() {
	    if (titleList == null) {
		titleList = new ArrayList<>();
	    }
	    return this.titleList;
	}

	/**
	 * Gets the derived view list.
	 *
	 * @return the derived view list
	 */
	public List<DerivedViewType> getDerivedViewList() {
	    if (derivedViewList == null) {
		derivedViewList = new ArrayList<>();
	    }
	    return this.derivedViewList;
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
	 * @param mainId
	 *            the new main id
	 */
	public void setMainId(String mainId) {
	    this.mainId = mainId;
	}

	public String getCreationClassCode() {
	    return creationClassCode;
	}

	public void setCreationClassCode(String creationClassCode) {
	    this.creationClassCode = creationClassCode;
	}

	public List<DerivedWork> getDerivedWorkParentList() {
	    if (derivedWorkParentList == null) {
		derivedWorkParentList = new ArrayList<>();
	    }
	    return this.derivedWorkParentList;
	}

	public String getCmoOriginCode() {
	    return cmoOriginCode;
	}

	public void setCmoOriginCode(String cmoOriginCode) {
	    this.cmoOriginCode = cmoOriginCode;
	}

	public BigDecimal getComponentPerc() {
	    return componentPerc;
	}

	public void setComponentPerc(BigDecimal componentPerc) {
	    this.componentPerc = componentPerc;
	}

    }

}
