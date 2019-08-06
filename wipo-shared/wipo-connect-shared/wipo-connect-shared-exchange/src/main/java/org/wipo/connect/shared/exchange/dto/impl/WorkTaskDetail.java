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

package org.wipo.connect.shared.exchange.dto.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.wipo.connect.common.dto.Identifiable;

/**
 * The Class WorkTaskDetail.
 *
 * @author minervini
 */
public class WorkTaskDetail implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 382097952762840779L;

    /** The id work task detail. */
    private Long idWorkTaskDetail;

    /** The fk work task item. */
    private Long fkWorkTaskItem;

    /** The duration. */
    private Long duration;

    /** The genre code. */
    private String genreCode;

    /** The release date. */
    private Date releaseDate;

    /** The type code. */
    private String typeCode;

    /** The registration date. */
    private Date registrationDate;

    /** The instrument list. */
    private List<String> instrumentCodeList;

    /** The work task detail title list. */
    private List<WorkTaskDetailTitle> titleList;

    /** The work performer list. */
    private List<WorkTaskDetailPerf> workPerformerList;

    /** The work identifier list. */
    private List<WorkTaskDetailIdent> workIdentifierList;

    /** The derived view list. */
    private List<WorkTaskDetailDv> derivedViewList;

    /** The work creation date. */
    private Date workCreationDate;

    /** The source type code. */
    private String sourceTypeCode;

    /** The domestic work. */
    private Boolean domesticWork;

    /** The excerpt type code. */
    private String excerptTypeCode;

    /** The composite type code. */
    private String compositeTypeCode;

    private String mainId;

    private String creationClassCode;

    private String countryOfProductionCode;

    private String categoryCode;

    private String catalogueNumber;

    private String support;

    private String label;

    private String language;

    private List<WorkTaskDetailDate> workDateList;

    private BigDecimal componentPerc;

    private List<WorkTaskDetailDerivedWork> derivedWorkList;

    @Override
    public Long getId() {
	return getIdWorkTaskDetail();
    }

    @Override
    public void setId(Long id) {
	setIdWorkTaskDetail(id);
    }

    /**
     * Gets the id work task detail.
     *
     * @return the id work task detail
     */
    public Long getIdWorkTaskDetail() {
	return idWorkTaskDetail;
    }

    /**
     * Sets the id work task detail.
     *
     * @param idWorkTaskDetail
     *            the new id work task detail
     */
    public void setIdWorkTaskDetail(Long idWorkTaskDetail) {
	this.idWorkTaskDetail = idWorkTaskDetail;
    }

    /**
     * Gets the fk work task item.
     *
     * @return the fk work task item
     */
    public Long getFkWorkTaskItem() {
	return fkWorkTaskItem;
    }

    /**
     * Sets the fk work task item.
     *
     * @param fkWorkTaskItem
     *            the new fk work task item
     */
    public void setFkWorkTaskItem(Long fkWorkTaskItem) {
	this.fkWorkTaskItem = fkWorkTaskItem;
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
     * @param duration
     *            the new duration
     */
    public void setDuration(Long duration) {
	this.duration = duration;
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
     * @param genreCode
     *            the new genre code
     */
    public void setGenreCode(String genreCode) {
	this.genreCode = genreCode;
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
     * @param releaseDate
     *            the new release date
     */
    public void setReleaseDate(Date releaseDate) {
	this.releaseDate = releaseDate;
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
     * @param typeCode
     *            the new type code
     */
    public void setTypeCode(String typeCode) {
	this.typeCode = typeCode;
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
     * @param registrationDate
     *            the new registration date
     */
    public void setRegistrationDate(Date registrationDate) {
	this.registrationDate = registrationDate;
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
	return instrumentCodeList;
    }

    /**
     * Sets the instrument code list.
     *
     * @param instrumentCodeList
     *            the new instrument code list
     */
    public void setInstrumentCodeList(List<String> instrumentCodeList) {
	this.instrumentCodeList = instrumentCodeList;
    }

    /**
     * Gets the title list.
     *
     * @return the title list
     */
    public List<WorkTaskDetailTitle> getTitleList() {
	if (titleList == null) {
	    titleList = new ArrayList<>();
	}
	return titleList;
    }

    /**
     * Sets the title list.
     *
     * @param titleList
     *            the new title list
     */
    public void setTitleList(List<WorkTaskDetailTitle> titleList) {
	this.titleList = titleList;
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
     * @param workCreationDate
     *            the new work creation date
     */
    public void setWorkCreationDate(Date workCreationDate) {
	this.workCreationDate = workCreationDate;
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
     * @param sourceTypeCode
     *            the new source type code
     */
    public void setSourceTypeCode(String sourceTypeCode) {
	this.sourceTypeCode = sourceTypeCode;
    }

    /**
     * Gets the domestic work.
     *
     * @return the domestic work
     */
    public Boolean getDomesticWork() {
	return domesticWork;
    }

    /**
     * Sets the domestic work.
     *
     * @param domesticWork
     *            the new domestic work
     */
    public void setDomesticWork(Boolean domesticWork) {
	this.domesticWork = domesticWork;
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
     * @param excerptTypeCode
     *            the new excerpt type code
     */
    public void setExcerptTypeCode(String excerptTypeCode) {
	this.excerptTypeCode = excerptTypeCode;
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
     * @param compositeTypeCode
     *            the new composite type code
     */
    public void setCompositeTypeCode(String compositeTypeCode) {
	this.compositeTypeCode = compositeTypeCode;
    }

    /**
     * Gets the work performer list.
     *
     * @return the work performer list
     */
    public List<WorkTaskDetailPerf> getWorkPerformerList() {
	if (workPerformerList == null) {
	    workPerformerList = new ArrayList<>();
	}
	return workPerformerList;
    }

    /**
     * Sets the work performer list.
     *
     * @param workPerformerList
     *            the new work performer list
     */
    public void setWorkPerformerList(List<WorkTaskDetailPerf> workPerformerList) {
	this.workPerformerList = workPerformerList;
    }

    /**
     * Gets the work identifier list.
     *
     * @return the work identifier list
     */
    public List<WorkTaskDetailIdent> getWorkIdentifierList() {
	if (workIdentifierList == null) {
	    workIdentifierList = new ArrayList<>();
	}
	return workIdentifierList;
    }

    /**
     * Sets the work identifier list.
     *
     * @param workIdentifierList
     *            the new work identifier list
     */
    public void setWorkIdentifierList(List<WorkTaskDetailIdent> workIdentifierList) {
	this.workIdentifierList = workIdentifierList;
    }

    /**
     * Gets the derived view list.
     *
     * @return the derived view list
     */
    public List<WorkTaskDetailDv> getDerivedViewList() {
	if (derivedViewList == null) {
	    derivedViewList = new ArrayList<>();
	}
	return derivedViewList;
    }

    /**
     * Sets the derived view list.
     *
     * @param derivedViewList
     *            the new derived view list
     */
    public void setDerivedViewList(List<WorkTaskDetailDv> derivedViewList) {
	this.derivedViewList = derivedViewList;
    }

    public String getMainId() {
	return mainId;
    }

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

    public List<WorkTaskDetailDate> getWorkDateList() {
	if (workDateList == null) {
	    workDateList = new ArrayList<>();
	}
	return workDateList;
    }

    public BigDecimal getComponentPerc() {
	return componentPerc;
    }

    public void setComponentPerc(BigDecimal componentPerc) {
	this.componentPerc = componentPerc;
    }

    public List<WorkTaskDetailDerivedWork> getDerivedWorkList() {
	if (derivedWorkList == null) {
	    derivedWorkList = new ArrayList<>();
	}
	return derivedWorkList;
    }

    public void setWorkDateList(List<WorkTaskDetailDate> workDateList) {
	this.workDateList = workDateList;
    }

    public void setDerivedWorkList(List<WorkTaskDetailDerivedWork> derivedWorkList) {
	this.derivedWorkList = derivedWorkList;
    }

}
