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
package org.wipo.connect.shared.exchange.restvo.external;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.wipo.connect.shared.exchange.dto.impl.Name;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author pasquale.minervini
 *
 */
@JsonInclude(value = Include.NON_EMPTY)
public class WorkRestVOResponse implements Serializable {

    private static final long serialVersionUID = 6418628237796465934L;

    private Long duration;

    private String genreCode;

    private Date workCreationDate;

    private Date releaseDate;

    private String typeCode;

    private String statusCode;

    private Date registrationDate;

    private String domain;

    private List<String> instrumentCodeList;

    private Date lastUpdateDate;

    private Boolean toBeSubmitted;

    private String sourceTypeCode;

    private String excerptTypeCode;

    private String compositeTypeCode;

    private String cmoOriginCode;

    private String mainId;

    private String catalogueNumber;

    private String support;

    private String language;

    private String label;

    private String countryOfProductionCode;

    private String categoryCode;

    private String creationClassCode;

    private BigDecimal componentPerc;

    private Long syncVersion;

    private BigDecimal score;

    private List<TitleVOResponse> titleList;
    private List<WorkIdentifierVOResponse> workIdentifierList;
    private List<OwnershipShareVOResponse> ownershipShareList;
    private List<DerivedViewVOResponse> derivedViewList;
    private List<WorkPerformerVOResponse> workPerformerList;
    private List<DerivedWorkVOResponse> derivedWorkList;
    private List<WorkDateVOResponse> workDateList;
    private List<DerivedWorkVOResponse> derivedWorkParentList;

    @JsonInclude(value = Include.NON_EMPTY)
    public static class TitleVOResponse implements Serializable {

	private static final long serialVersionUID = 8062729543312910360L;

	private String typeCode;

	private String description;

	public String getTypeCode() {
	    return typeCode;
	}

	public void setTypeCode(String typeCode) {
	    this.typeCode = typeCode;
	}

	public String getDescription() {
	    return description;
	}

	public void setDescription(String description) {
	    this.description = description;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class WorkIdentifierVOResponse implements Serializable {
	private static final long serialVersionUID = -8793838728036546852L;

	private String code;

	private String value;

	private String acronym;

	public String getCode() {
	    return code;
	}

	public void setCode(String code) {
	    this.code = code;
	}

	public String getValue() {
	    return value;
	}

	public void setValue(String value) {
	    this.value = value;
	}

	public String getAcronym() {
	    return acronym;
	}

	public void setAcronym(String acronym) {
	    this.acronym = acronym;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class WorkPerformerVOResponse implements Serializable {

	private static final long serialVersionUID = -3067690231701484998L;
	private String performerName;

	public String getPerformerName() {
	    return performerName;
	}

	public void setPerformerName(String performerName) {
	    this.performerName = performerName;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class DerivedWorkVOResponse implements Serializable {

	private static final long serialVersionUID = 5723656848619259392L;
	private String mainIdWork;
	private Long fkParentWork;
	private WorkRestVOResponse work;

	private Long weight;
	private Long trackNumber;

	public String getMainIdWork() {
	    return mainIdWork;
	}

	public void setMainIdWork(String mainIdWork) {
	    this.mainIdWork = mainIdWork;
	}

	public Long getFkParentWork() {
	    return fkParentWork;
	}

	public void setFkParentWork(Long fkParentWork) {
	    this.fkParentWork = fkParentWork;
	}

	public WorkRestVOResponse getWork() {
	    return work;
	}

	public void setWork(WorkRestVOResponse work) {
	    this.work = work;
	}

	public Long getWeight() {
	    return weight;
	}

	public void setWeight(Long weight) {
	    this.weight = weight;
	}

	public Long getTrackNumber() {
	    return trackNumber;
	}

	public void setTrackNumber(Long trackNumber) {
	    this.trackNumber = trackNumber;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class WorkDateVOResponse implements Serializable {

	private static final long serialVersionUID = 5782860894727225729L;

	private String dateTypeCode;

	private Date dateValue;

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

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class OwnershipShareVOResponse implements Serializable {

	private static final long serialVersionUID = 3596300880321254831L;
	private Name name;
	private String roleCode;
	private BigDecimal shareValue;

	public Name getName() {
	    return name;
	}

	public void setName(Name name) {
	    this.name = name;
	}

	public String getRoleCode() {
	    return roleCode;
	}

	public void setRoleCode(String roleCode) {
	    this.roleCode = roleCode;
	}

	public BigDecimal getShareValue() {
	    return shareValue;
	}

	public void setShareValue(BigDecimal shareValue) {
	    this.shareValue = shareValue;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class DerivedViewVOResponse implements Serializable {
	private static final long serialVersionUID = -4004616656418631566L;

	private String territoryFormula;

	private List<DerivedViewNameVOResponse> derivedViewNameList;

	public String getTerritoryFormula() {
	    return territoryFormula;
	}

	public void setTerritoryFormula(String territoryFormula) {
	    this.territoryFormula = territoryFormula;
	}

	public List<DerivedViewNameVOResponse> getDerivedViewNameList() {
	    return derivedViewNameList;
	}

	public void setDerivedViewNameList(List<DerivedViewNameVOResponse> derivedViewNameList) {
	    this.derivedViewNameList = derivedViewNameList;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class DerivedViewNameVOResponse implements Serializable {

	private static final long serialVersionUID = 2749435158031954102L;

	private NameRestVOResponse name;

	private String roleCode;

	private Long creatorRefIndex;

	private String creatorRefMainId;

	private List<DerivedViewNameShareVOResponse> derivedViewNameShareList;

	private String sourceType;

	private String sourceTypeCode;

	private Set<String> affiliatedCmos;

	public NameRestVOResponse getName() {
	    return name;
	}

	public void setName(NameRestVOResponse name) {
	    this.name = name;
	}

	public String getRoleCode() {
	    return roleCode;
	}

	public void setRoleCode(String roleCode) {
	    this.roleCode = roleCode;
	}

	public Long getCreatorRefIndex() {
	    return creatorRefIndex;
	}

	public void setCreatorRefIndex(Long creatorRefIndex) {
	    this.creatorRefIndex = creatorRefIndex;
	}

	public List<DerivedViewNameShareVOResponse> getDerivedViewNameShareList() {
	    return derivedViewNameShareList;
	}

	public void setDerivedViewNameShareList(List<DerivedViewNameShareVOResponse> derivedViewNameShareList) {
	    this.derivedViewNameShareList = derivedViewNameShareList;
	}

	public String getSourceType() {
	    return sourceType;
	}

	public void setSourceType(String sourceType) {
	    this.sourceType = sourceType;
	}

	public String getSourceTypeCode() {
	    return sourceTypeCode;
	}

	public void setSourceTypeCode(String sourceTypeCode) {
	    this.sourceTypeCode = sourceTypeCode;
	}

	public String getCreatorRefMainId() {
	    return creatorRefMainId;
	}

	public void setCreatorRefMainId(String creatorRefMainId) {
	    this.creatorRefMainId = creatorRefMainId;
	}

	public Set<String> getAffiliatedCmos() {
	    return affiliatedCmos;
	}

	public void setAffiliatedCmos(Set<String> affiliatedCmos) {
	    this.affiliatedCmos = affiliatedCmos;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class DerivedViewNameShareVOResponse implements Serializable {

	private static final long serialVersionUID = 126751441635497785L;

	private String rightTypeCode;

	private BigDecimal shareValue;

	private List<InterestedPartyRestVOResponse.CmoVOResponse> cmoList;

	public String getRightTypeCode() {
	    return rightTypeCode;
	}

	public void setRightTypeCode(String rightTypeCode) {
	    this.rightTypeCode = rightTypeCode;
	}

	public BigDecimal getShareValue() {
	    return shareValue;
	}

	public void setShareValue(BigDecimal shareValue) {
	    this.shareValue = shareValue;
	}

	public List<InterestedPartyRestVOResponse.CmoVOResponse> getCmoList() {
	    return cmoList;
	}

	public void setCmoList(List<InterestedPartyRestVOResponse.CmoVOResponse> cmoList) {
	    this.cmoList = cmoList;
	}

    }

    public Long getDuration() {
	return duration;
    }

    public void setDuration(Long duration) {
	this.duration = duration;
    }

    public String getGenreCode() {
	return genreCode;
    }

    public void setGenreCode(String genreCode) {
	this.genreCode = genreCode;
    }

    public Date getWorkCreationDate() {
	return workCreationDate;
    }

    public void setWorkCreationDate(Date workCreationDate) {
	this.workCreationDate = workCreationDate;
    }

    public Date getReleaseDate() {
	return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
	this.releaseDate = releaseDate;
    }

    public String getTypeCode() {
	return typeCode;
    }

    public void setTypeCode(String typeCode) {
	this.typeCode = typeCode;
    }

    public String getStatusCode() {
	return statusCode;
    }

    public void setStatusCode(String statusCode) {
	this.statusCode = statusCode;
    }

    public Date getRegistrationDate() {
	return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
	this.registrationDate = registrationDate;
    }

    public String getDomain() {
	return domain;
    }

    public void setDomain(String domain) {
	this.domain = domain;
    }

    public List<String> getInstrumentCodeList() {
	return instrumentCodeList;
    }

    public void setInstrumentCodeList(List<String> instrumentCodeList) {
	this.instrumentCodeList = instrumentCodeList;
    }

    public Date getLastUpdateDate() {
	return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
	this.lastUpdateDate = lastUpdateDate;
    }

    public Boolean getToBeSubmitted() {
	return toBeSubmitted;
    }

    public void setToBeSubmitted(Boolean toBeSubmitted) {
	this.toBeSubmitted = toBeSubmitted;
    }

    public String getSourceTypeCode() {
	return sourceTypeCode;
    }

    public void setSourceTypeCode(String sourceTypeCode) {
	this.sourceTypeCode = sourceTypeCode;
    }

    public String getExcerptTypeCode() {
	return excerptTypeCode;
    }

    public void setExcerptTypeCode(String excerptTypeCode) {
	this.excerptTypeCode = excerptTypeCode;
    }

    public String getCompositeTypeCode() {
	return compositeTypeCode;
    }

    public void setCompositeTypeCode(String compositeTypeCode) {
	this.compositeTypeCode = compositeTypeCode;
    }

    public String getCmoOriginCode() {
	return cmoOriginCode;
    }

    public void setCmoOriginCode(String cmoOriginCode) {
	this.cmoOriginCode = cmoOriginCode;
    }

    public String getMainId() {
	return mainId;
    }

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

    public List<TitleVOResponse> getTitleList() {
	return titleList;
    }

    public void setTitleList(List<TitleVOResponse> titleList) {
	this.titleList = titleList;
    }

    public List<WorkIdentifierVOResponse> getWorkIdentifierList() {
	return workIdentifierList;
    }

    public void setWorkIdentifierList(List<WorkIdentifierVOResponse> workIdentifierList) {
	this.workIdentifierList = workIdentifierList;
    }

    public List<OwnershipShareVOResponse> getOwnershipShareList() {
	return ownershipShareList;
    }

    public void setOwnershipShareList(List<OwnershipShareVOResponse> ownershipShareList) {
	this.ownershipShareList = ownershipShareList;
    }

    public List<DerivedViewVOResponse> getDerivedViewList() {
	return derivedViewList;
    }

    public void setDerivedViewList(List<DerivedViewVOResponse> derivedViewList) {
	this.derivedViewList = derivedViewList;
    }

    public List<WorkPerformerVOResponse> getWorkPerformerList() {
	return workPerformerList;
    }

    public void setWorkPerformerList(List<WorkPerformerVOResponse> workPerformerList) {
	this.workPerformerList = workPerformerList;
    }

    public List<DerivedWorkVOResponse> getDerivedWorkList() {
	return derivedWorkList;
    }

    public void setDerivedWorkList(List<DerivedWorkVOResponse> derivedWorkList) {
	this.derivedWorkList = derivedWorkList;
    }

    public List<WorkDateVOResponse> getWorkDateList() {
	return workDateList;
    }

    public void setWorkDateList(List<WorkDateVOResponse> workDateList) {
	this.workDateList = workDateList;
    }

    public List<DerivedWorkVOResponse> getDerivedWorkParentList() {
	return derivedWorkParentList;
    }

    public void setDerivedWorkParentList(List<DerivedWorkVOResponse> derivedWorkParentList) {
	this.derivedWorkParentList = derivedWorkParentList;
    }

}
