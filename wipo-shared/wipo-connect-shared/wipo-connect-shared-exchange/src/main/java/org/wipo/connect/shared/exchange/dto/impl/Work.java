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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Creatable;
import org.wipo.connect.common.dto.Identifiable;
import org.wipo.connect.common.dto.Updatable;
import org.wipo.connect.shared.exchange.enumerator.IdentifierTypeEnum;
import org.wipo.connect.shared.exchange.enumerator.TitleTypeCodeEnum;

/**
 * The Class Work.
 */
public class Work extends BaseDTO implements Identifiable, Updatable, Creatable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -6638790062607823103L;

    /** The id work. */
    private Long idWork;

    private Long duration;

    /** The genre code. */
    private String genreCode;

    /** The work creation date. */
    private Date workCreationDate;

    // /** The creation date. */
    // private Date creationDate;

    /** The release date. */
    private Date releaseDate;

    /** The fk type. */
    private Long fkType;

    /** The type code. */
    private String typeCode;

    /** The fk status. */
    private Long fkStatus;

    /** The status code. */
    private String statusCode;

    /** The registration date. */
    private Date registrationDate;

    /** The domain. */
    private String domain;

    /** The instrument code list. */
    private List<String> instrumentCodeList;

    /** The title list. */
    private List<Title> titleList;

    /** The work identifier list. */
    private List<WorkIdentifierFlat> workIdentifierList;

    /** The derived view list. */
    private List<DerivedView> derivedViewList;

    /** The fk Source Type. */
    private Long fkSourceType;

    /** The domestic Work flag. */
    private boolean domesticWork;

    /** The last Update Date. */
    private Date lastUpdateDate;

    /** The work performer list. */
    private List<WorkPerformer> workPerformerList;

    /** The to be submitted. */
    private Boolean toBeSubmitted;

    /** The derived work list. */
    private List<DerivedWork> derivedWorkList;

    /** The Source Type code. */
    private String sourceTypeCode;

    private Long fkCmo;

    private String cmoOriginCode;

    private String mainId;

    /** The catalogue number. */
    private String catalogueNumber;

    /** The support. */
    private String support;

    /** The fk country of production. */
    private Long fkCountryOfProduction;

    /** The work date list. */
    private List<WorkDate> workDateList;

    /** The language. */
    private String language;

    private String label;

    private String countryOfProductionCode;

    private String categoryCode;

    private Long fkCreationClass;

    private String creationClassCode;

    private BigDecimal componentPerc;

    private List<DerivedWork> derivedWorkParentList;

    private Long syncVersion;

    private BigDecimal score;

    /**
     * Gets the cmo origin code.
     *
     * @return the cmo origin code
     */
    public String getCmoOriginCode() {
	return cmoOriginCode;
    }

    /**
     * Sets the cmo origin code.
     *
     * @param cmoOriginCode
     *            the new cmo origin code
     */
    public void setCmoOriginCode(String cmoOriginCode) {
	this.cmoOriginCode = cmoOriginCode;
    }

    /**
     * Gets the fk cmo.
     *
     * @return the fk cmo
     */
    public Long getFkCmo() {
	return fkCmo;
    }

    /**
     * Sets the fk cmo.
     *
     * @param fkCmo
     *            the new fk cmo
     */
    public void setFkCmo(Long fkCmo) {
	this.fkCmo = fkCmo;
    }

    /**
     * Gets the derived view list.
     *
     * @return the derived view list
     */
    public List<DerivedView> getDerivedViewList() {
	if (this.derivedViewList == null) {
	    this.derivedViewList = new ArrayList<>();
	}
	return this.derivedViewList;
    }

    /**
     * Gets the domain.
     *
     * @return the domain
     */
    public String getDomain() {
	return this.domain;
    }

    /**
     * Gets the duration.
     *
     * @return the duration
     */
    public Long getDuration() {
	return this.duration;
    }

    /**
     * Gets the fk status.
     *
     * @return the fk status
     */
    public Long getFkStatus() {
	return this.fkStatus;
    }

    /**
     * Gets the fk type.
     *
     * @return the fk type
     */
    public Long getFkType() {
	return this.fkType;
    }

    /**
     * Gets the genre code.
     *
     * @return the genre code
     */
    public String getGenreCode() {
	return this.genreCode;
    }

    @Override
    public Long getId() {
	return getIdWork();
    }

    /**
     * Gets the id work.
     *
     * @return the id work
     */
    public Long getIdWork() {
	return this.idWork;
    }

    /**
     * Gets the instrument code list.
     *
     * @return the instrument code list
     */
    public List<String> getInstrumentCodeList() {
	if (this.instrumentCodeList == null) {
	    this.instrumentCodeList = new ArrayList<>();
	}
	return this.instrumentCodeList;
    }

    /**
     * Gets the registration date.
     *
     * @return the registration date
     */
    public Date getRegistrationDate() {
	return this.registrationDate;
    }

    /**
     * Gets the release date.
     *
     * @return the release date
     */
    public Date getReleaseDate() {
	return this.releaseDate;
    }

    /**
     * Gets the status code.
     *
     * @return the status code
     */
    public String getStatusCode() {
	return this.statusCode;
    }

    /**
     * Gets the title list.
     *
     * @return the title list
     */
    public List<Title> getTitleList() {
	if (this.titleList == null) {
	    this.titleList = new ArrayList<>();
	}
	return this.titleList;
    }

    /**
     * Gets the type code.
     *
     * @return the type code
     */
    public String getTypeCode() {
	return this.typeCode;
    }

    /**
     * Gets the iswc archived id list.
     *
     * @return the iswc archived id list
     */
    public List<String> getIswcList() {
	List<String> iswcArcList = new ArrayList<>();
	for (WorkIdentifierFlat wid : getWorkIdentifierList()) {
	    if (wid.getCode().equals(IdentifierTypeEnum.ISWC.name())) {
		iswcArcList.add(wid.getValue());
	    }
	}
	return iswcArcList;
    }

    /**
     * Gets the work identifier list.
     *
     * @return the work identifier list
     */
    public List<WorkIdentifierFlat> getWorkIdentifierList() {
	if (this.workIdentifierList == null) {
	    this.workIdentifierList = new ArrayList<>();
	}
	return this.workIdentifierList;
    }

    // /**
    // * Sets the creation date.
    // *
    // * @param creationDate
    // * the new creation date
    // */
    // public void setCreationDate(Date creationDate) {
    // this.creationDate = creationDate;
    // }

    /**
     * Sets the derived view list.
     *
     * @param derivedViewList
     *            the new derived view list
     */
    public void setDerivedViewList(List<DerivedView> derivedViewList) {
	this.derivedViewList = derivedViewList;
    }

    /**
     * Sets the domain.
     *
     * @param domain
     *            the new domain
     */
    public void setDomain(String domain) {
	this.domain = domain;
    }

    public void setDuration(Long duration) {
	this.duration = duration;
    }

    public void setFkStatus(Long fkStatus) {
	this.fkStatus = fkStatus;
    }

    /**
     * Sets the fk type.
     *
     * @param fkType
     *            the new fk type
     */
    public void setFkType(Long fkType) {
	this.fkType = fkType;
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

    @Override
    public void setId(Long id) {
	setIdWork(id);

    }

    /**
     * Sets the id work.
     *
     * @param idWork
     *            the new id work
     */
    public void setIdWork(Long idWork) {
	this.idWork = idWork;
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
     * Sets the registration date.
     *
     * @param registrationDate
     *            the new registration date
     */
    public void setRegistrationDate(Date registrationDate) {
	this.registrationDate = registrationDate;
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
     * Sets the status code.
     *
     * @param statusCode
     *            the new status code
     */
    public void setStatusCode(String statusCode) {
	this.statusCode = statusCode;
    }

    /**
     * Sets the title list.
     *
     * @param titleList
     *            the new title list
     */
    public void setTitleList(List<Title> titleList) {
	this.titleList = titleList;
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
     * Sets the work identifier list.
     *
     * @param workIdentifierList
     *            the new work identifier list
     */
    public void setWorkIdentifierList(List<WorkIdentifierFlat> workIdentifierList) {
	this.workIdentifierList = workIdentifierList;
    }

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
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
     * Gets the fk source type.
     *
     * @return the fk source type
     */
    public Long getFkSourceType() {
	return fkSourceType;
    }

    /**
     * Sets the fk source type.
     *
     * @param fkSourceType
     *            the new fk source type
     */
    public void setFkSourceType(Long fkSourceType) {
	this.fkSourceType = fkSourceType;
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
     * @param domesticWork
     *            the new domestic work
     */
    public void setDomesticWork(boolean domesticWork) {
	this.domesticWork = domesticWork;
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
     * @param lastUpdateDate
     *            the new last update date
     */
    public void setLastUpdateDate(Date lastUpdateDate) {
	this.lastUpdateDate = lastUpdateDate;
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
	return workPerformerList;
    }

    /**
     * Sets the work performer list.
     *
     * @param workPerformerList
     *            the new work performer list
     */
    public void setWorkPerformerList(List<WorkPerformer> workPerformerList) {
	this.workPerformerList = workPerformerList;
    }

    /**
     * Gets the to be submitted.
     *
     * @return the to be submitted
     */
    public Boolean getToBeSubmitted() {
	return toBeSubmitted;
    }

    /**
     * Sets the to be submitted.
     *
     * @param toBeSubmitted
     *            the new to be submitted
     */
    public void setToBeSubmitted(Boolean toBeSubmitted) {
	this.toBeSubmitted = toBeSubmitted;
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
	return derivedWorkList;
    }

    /**
     * Sets the derived work list.
     *
     * @param derivedWorkList
     *            the new derived work list
     */
    public void setDerivedWorkList(List<DerivedWork> derivedWorkList) {
	this.derivedWorkList = derivedWorkList;
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
     * Gets the original title.
     *
     * @return the original title
     */
    public String getOriginalTitle() {
	String ot = null;
	for (Title title : getTitleList()) {
	    if (StringUtils.equals(title.getTypeCode(), TitleTypeCodeEnum.OT.name())) {
		ot = title.getDescription();
		break;
	    }
	}
	return ot;
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

    public Long getFkCountryOfProduction() {
	return fkCountryOfProduction;
    }

    public void setFkCountryOfProduction(Long fkCountryOfProduction) {
	this.fkCountryOfProduction = fkCountryOfProduction;
    }

    public List<WorkDate> getWorkDateList() {
	if (workDateList == null) {
	    workDateList = new ArrayList<>();
	}
	return workDateList;
    }

    public void setWorkDateList(List<WorkDate> workDateList) {
	this.workDateList = workDateList;
    }

    public String getLanguage() {
	return language;
    }

    public void setLanguage(String language) {
	this.language = language;
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

    public String getCategoryCode() {
	return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
	this.categoryCode = categoryCode;
    }

    public Long getFkCreationClass() {
	return fkCreationClass;
    }

    public void setFkCreationClass(Long fkCreationClass) {
	this.fkCreationClass = fkCreationClass;
    }

    public String getCreationClassCode() {
	return creationClassCode;
    }

    public void setCreationClassCode(String creationClassCode) {
	this.creationClassCode = creationClassCode;
    }

    public BigDecimal getComponentPerc() {
	return componentPerc;
    }

    public void setComponentPerc(BigDecimal componentPerc) {
	this.componentPerc = componentPerc;
    }

    public List<DerivedWork> getDerivedWorkParentList() {
	if (derivedWorkParentList == null) {
	    derivedWorkParentList = new ArrayList<>();
	}
	return derivedWorkParentList;
    }

    public void setDerivedWorkParentList(List<DerivedWork> derivedWorkParentList) {
	this.derivedWorkParentList = derivedWorkParentList;
    }

    public Long getSyncVersion() {
	return syncVersion;
    }

    public void setSyncVersion(Long syncVersion) {
	this.syncVersion = syncVersion;
    }

    public BigDecimal getScore() {
	return score;
    }

    public void setScore(BigDecimal score) {
	this.score = score;
    }

}
