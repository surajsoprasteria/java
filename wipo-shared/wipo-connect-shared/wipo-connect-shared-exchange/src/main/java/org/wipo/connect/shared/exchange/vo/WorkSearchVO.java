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

package org.wipo.connect.shared.exchange.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Identifiable;

@SuppressWarnings({ "squid:S1948" })
public class WorkSearchVO implements Serializable, Identifiable {
    private static final long serialVersionUID = 4851306326369264339L;

    private Long idWork;

    private String title;

    private List<String> domain;

    private List<RightOwnerSearchVO> rightOwnerList;

    private String statusCode;

    private Boolean onlyRegistered;

    private Boolean originalTitles;

    private String sourceType;

    private String workType;

    private Date registrationDate;

    private String performer;

    private String identifier;

    private String mainId;

    private String isw;

    private String code;

    private Boolean isToShowDeleted = false;

    private Boolean forceValidStatus;

    private String wipoLocalId;

    private Boolean disableOriginCheck = false;

    private String territoryFormula;

    private String fullText;

    private String catalogueNumber;
    private String countryOfProductionCode;
    private String categoryCode;
    private String label;
    private List<String> creationClassCodeList;
    private String dateTypeCode;
    private Date dateValue;

    private List<RightOwnerVO> rightOwnerListSearch;

    private String originalTitleTrimmed;
    private List<IdentifierSearchVO> identifierSearchVOList;

    private List<Long> idList;

    private Boolean onlyMainId = false;

    private Boolean lightSearch;

    private Integer paginationStart;
    private Integer paginationLength;
    private Integer paginationDraw;
    private String orderByExpression;

    private List<Long> idToExcludeList;

    private Boolean isSimpleSearch = false;

    public String getTerritoryFormula() {
	return territoryFormula;
    }

    public void setTerritoryFormula(String territoryFormula) {
	this.territoryFormula = territoryFormula;
    }

    public List<String> getDomain() {
	if (this.domain == null) {
	    this.domain = new ArrayList<>();
	}
	return this.domain;
    }

    public String getDomainString() {
	return String.join(", ", getDomain());
    }

    @Override
    public Long getId() {
	return getIdWork();
    }

    public Long getIdWork() {
	return this.idWork;
    }

    public Boolean getOnlyRegistered() {
	return this.onlyRegistered;
    }

    public String getStatusCode() {
	return this.statusCode;
    }

    public String getTitle() {
	return this.title;
    }

    public void setDomain(List<String> domain) {
	this.domain = domain;
    }

    @Override
    public void setId(Long id) {
	setIdWork(id);
    }

    public void setIdWork(Long idWork) {
	this.idWork = idWork;
    }

    public void setOnlyRegistered(Boolean onlyRegistered) {
	this.onlyRegistered = onlyRegistered;
    }

    public void setStatusCode(String statusCode) {
	this.statusCode = statusCode;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
    }

    public Boolean getOriginalTitles() {
	return originalTitles;
    }

    public void setOriginalTitles(Boolean originalTitles) {
	this.originalTitles = originalTitles;
    }

    public String getSourceType() {
	return sourceType;
    }

    public void setSourceType(String sourceType) {
	this.sourceType = sourceType;
    }

    public String getWorkType() {
	return workType;
    }

    public void setWorkType(String workType) {
	this.workType = workType;
    }

    public Date getRegistrationDate() {
	return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
	this.registrationDate = registrationDate;
    }

    public String getPerformer() {
	return performer;
    }

    public void setPerformer(String performer) {
	this.performer = performer;
    }

    public String getIdentifier() {
	return identifier;
    }

    public void setIdentifier(String identifier) {
	this.identifier = identifier;
    }

    public String getMainId() {
	return mainId;
    }

    public void setMainId(String mainId) {
	this.mainId = mainId;
    }

    public String getIsw() {
	return isw;
    }

    public void setIsw(String isw) {
	this.isw = isw;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public Boolean getIsToShowDeleted() {
	return isToShowDeleted;
    }

    public void setIsToShowDeleted(Boolean isToShowDeleted) {
	this.isToShowDeleted = isToShowDeleted;
    }

    public Boolean getForceValidStatus() {
	return forceValidStatus;
    }

    public void setForceValidStatus(Boolean forceValidStatus) {
	this.forceValidStatus = forceValidStatus;
    }

    public String getWipoLocalId() {
	return wipoLocalId;
    }

    public void setWipoLocalId(String wipoLocalId) {
	this.wipoLocalId = wipoLocalId;
    }

    public Boolean getDisableOriginCheck() {
	return disableOriginCheck;
    }

    public void setDisableOriginCheck(Boolean disableOriginCheck) {
	this.disableOriginCheck = disableOriginCheck;
    }

    public String getFullText() {
	return fullText;
    }

    public void setFullText(String fullText) {
	this.fullText = fullText;
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

    public List<String> getCreationClassCodeList() {
	if (creationClassCodeList == null) {
	    creationClassCodeList = new ArrayList<>();
	}
	return creationClassCodeList;
    }

    public void setCreationClassCodeList(List<String> creationClassCodeList) {
	this.creationClassCodeList = creationClassCodeList;
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

    public List<RightOwnerSearchVO> getRightOwnerList() {
	if (rightOwnerList == null) {
	    rightOwnerList = new ArrayList<>();
	}
	return rightOwnerList;
    }

    public void setRightOwnerList(List<RightOwnerSearchVO> rightOwnerList) {
	this.rightOwnerList = rightOwnerList;
    }

    public List<RightOwnerVO> getRightOwnerListSearch() {
	if (rightOwnerListSearch == null) {
	    rightOwnerListSearch = new ArrayList<>();
	}
	return rightOwnerListSearch;
    }

    public void setRightOwnerListSearch(List<RightOwnerVO> rightOwnerListSearch) {
	this.rightOwnerListSearch = rightOwnerListSearch;
    }

    public String getOriginalTitleTrimmed() {
	return originalTitleTrimmed;
    }

    public void setOriginalTitleTrimmed(String originalTitleTrimmed) {
	this.originalTitleTrimmed = originalTitleTrimmed;
    }

    public List<IdentifierSearchVO> getIdentifierSearchVOList() {
	if (identifierSearchVOList == null) {
	    identifierSearchVOList = new ArrayList<>();
	}
	return identifierSearchVOList;
    }

    public void setIdentifierSearchVOList(List<IdentifierSearchVO> identifierSearchVOList) {
	this.identifierSearchVOList = identifierSearchVOList;
    }

    public Boolean getOnlyMainId() {
	return onlyMainId;
    }

    public void setOnlyMainId(Boolean onlyMainId) {
	this.onlyMainId = onlyMainId;
    }

    public List<Long> getIdList() {
	if (idList == null) {
	    idList = new ArrayList<>();
	}
	return idList;
    }

    public void setIdList(List<Long> idList) {
	this.idList = idList;
    }

    public Boolean getLightSearch() {
	return lightSearch;
    }

    public void setLightSearch(Boolean lightSearch) {
	this.lightSearch = lightSearch;
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

    public void setIdToExcludeList(List<Long> mainIdToExcludelist) {
	this.idToExcludeList = mainIdToExcludelist;
    }

    public Boolean getIsSimpleSearch() {
	return isSimpleSearch;
    }

    public void setIsSimpleSearch(Boolean isSimpleSearch) {
	this.isSimpleSearch = isSimpleSearch;
    }

}
