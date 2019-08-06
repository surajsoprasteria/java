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

import org.wipo.connect.common.input.ContextType;
import org.wipo.connect.common.utils.XmlDateAdapter;

/**
 * The Class InternationallyRegisterRequest.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "context", "taskCode", "taskItemRequest" })
@XmlRootElement(name = "InternationallyRegisterRequest")
public class InternationallyRegisterRequest {

    @XmlElement(required = true)
    protected ContextType context;
    @XmlElement(required = true)
    protected String taskCode;
    @XmlElement(required = true)
    protected List<InternationallyRegisterRequest.TaskItemRequest> taskItemRequest;

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
     * Gets the task code.
     *
     * @return the task code
     */
    public String getTaskCode() {
	return taskCode;
    }

    /**
     * Sets the task code.
     *
     * @param value
     *            the new task code
     */
    public void setTaskCode(String value) {
	this.taskCode = value;
    }

    /**
     * Gets the task item request.
     *
     * @return the task item request
     */
    public List<InternationallyRegisterRequest.TaskItemRequest> getTaskItemRequest() {
	if (taskItemRequest == null) {
	    taskItemRequest = new ArrayList<InternationallyRegisterRequest.TaskItemRequest>();
	}
	return this.taskItemRequest;
    }

    /**
     * The Class TaskItemRequest.
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "progr", "itemCode", "workTaskDetail" })
    public static class TaskItemRequest {

	protected long progr;
	@XmlElement(required = true)
	protected String itemCode;
	@XmlElement(required = true)
	protected InternationallyRegisterRequest.TaskItemRequest.WorkTaskDetail workTaskDetail;

	/**
	 * Gets the progr.
	 *
	 * @return the progr
	 */
	public long getProgr() {
	    return progr;
	}

	/**
	 * Sets the progr.
	 *
	 * @param value
	 *            the new progr
	 */
	public void setProgr(long value) {
	    this.progr = value;
	}

	/**
	 * Gets the item code.
	 *
	 * @return the item code
	 */
	public String getItemCode() {
	    return itemCode;
	}

	/**
	 * Sets the item code.
	 *
	 * @param value
	 *            the new item code
	 */
	public void setItemCode(String value) {
	    this.itemCode = value;
	}

	/**
	 * Gets the work task detail.
	 *
	 * @return the work task detail
	 */
	public InternationallyRegisterRequest.TaskItemRequest.WorkTaskDetail getWorkTaskDetail() {
	    return workTaskDetail;
	}

	/**
	 * Sets the work task detail.
	 *
	 * @param value
	 *            the new work task detail
	 */
	public void setWorkTaskDetail(InternationallyRegisterRequest.TaskItemRequest.WorkTaskDetail value) {
	    this.workTaskDetail = value;
	}

	/**
	 * The Class WorkTaskDetail.
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "idWork", "duration", "genreCode", "typeCode", "releaseDate", "registrationDate", "workCreationDate", "sourceTypeCode", "domesticWork",
		"excerptTypeCode", "compositeTypeCode", "workPerformerList", "derivedWorkList", "instrumentCodeList", "identifierList", "titleList", "derivedViewList", "mainId",
		"creationClassCode", "countryOfProductionCode", "categoryCode", "catalogueNumber", "support", "label", "language", "workDateList", "componentPerc"

	})
	public static class WorkTaskDetail {

	    protected long idWork;
	    @XmlElement(nillable = true)
	    protected Long duration;
	    @XmlElement(required = true, nillable = true)
	    protected String genreCode;
	    @XmlElement(required = true, nillable = true)
	    protected String typeCode;
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
	    protected Boolean domesticWork;
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

	    @XmlElement(required = true)
	    protected String creationClassCode;
	    protected String countryOfProductionCode;
	    protected String categoryCode;
	    protected String catalogueNumber;
	    protected String support;
	    protected String label;
	    protected String language;
	    protected List<WorkDate> workDateList;
	    @XmlElement(required = true)
	    protected BigDecimal componentPerc;

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
	     * @return the boolean
	     */
	    public Boolean isDomesticWork() {
		return domesticWork;
	    }

	    /**
	     * Sets the domestic work.
	     *
	     * @param value
	     *            the new domestic work
	     */
	    public void setDomesticWork(Boolean value) {
		this.domesticWork = value;
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
		    workPerformerList = new ArrayList<WorkPerformer>();
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
		    derivedWorkList = new ArrayList<DerivedWork>();
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
		    instrumentCodeList = new ArrayList<String>();
		}
		return this.instrumentCodeList;
	    }

	    /**
	     * Gets the identifier list.
	     *
	     * @return the identifier list
	     */
	    public List<FlatType> getIdentifierList() {
		if (identifierList == null) {
		    identifierList = new ArrayList<FlatType>();
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
		    titleList = new ArrayList<TitleType>();
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
		    derivedViewList = new ArrayList<DerivedViewType>();
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

	    public String getCatalogueNumber() {
		return catalogueNumber;
	    }

	    public void setCatalogueNumber(String catalogueNumber) {
		this.catalogueNumber = catalogueNumber;
	    }

	    public String getSupport() {
		return support;
	    }

	    public void setSupport(String support) {
		this.support = support;
	    }

	    public String getLabel() {
		return label;
	    }

	    public void setLabel(String label) {
		this.label = label;
	    }

	    public String getLanguage() {
		return language;
	    }

	    public void setLanguage(String language) {
		this.language = language;
	    }

	    public List<WorkDate> getWorkDateList() {
		if (workDateList == null) {
		    workDateList = new ArrayList<WorkDate>();
		}
		return workDateList;
	    }

	    public void setWorkDateList(List<WorkDate> workDateList) {
		this.workDateList = workDateList;
	    }

	    public BigDecimal getComponentPerc() {
		return componentPerc;
	    }

	    public void setComponentPerc(BigDecimal componentPerc) {
		this.componentPerc = componentPerc;
	    }

	    public Boolean getDomesticWork() {
		return domesticWork;
	    }

	    public void setDerivedWorkList(List<DerivedWork> derivedWorkList) {
		this.derivedWorkList = derivedWorkList;
	    }

	    public void setInstrumentCodeList(List<String> instrumentCodeList) {
		this.instrumentCodeList = instrumentCodeList;
	    }

	    public void setIdentifierList(List<FlatType> identifierList) {
		this.identifierList = identifierList;
	    }

	    public void setDerivedViewList(List<DerivedViewType> derivedViewList) {
		this.derivedViewList = derivedViewList;
	    }

	}

    }

}
