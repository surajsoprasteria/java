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

@SuppressWarnings({ "squid:S1948" })
public class InterestedPartySearchVO implements Serializable {

    private static final long serialVersionUID = -669048326974909807L;

    private Long id;

    private String domain;

    private String identifier;

    private String mainId;

    private String statusCode;

    private Date birthFoundationDate;

    private String gender;

    private String type;

    private String birthPlace;

    private String birthCountryCode;

    private String citizenship;

    private String lastCompanyName;

    private String firstName;

    private String nameType;

    private String cmoAcronym;

    private Date dateFrom;

    private Date dateTo;

    private String code;

    private Boolean isToShowDeleted = false;

    private Boolean forceValidStatus;

    private Boolean disableOriginCheck = false;

    private String fullText;

    private List<String> creationClassCodeList;

    private String wipoLocalId;

    private List<String> nameMainIdList;

    private List<String> nameTypeExcludeList;

    private Boolean onlyMainId = false;

    private List<Long> idList;

    private Boolean lightSearch;

    private Integer paginationStart;
    private Integer paginationLength;
    private Integer paginationDraw;
    private String orderByExpression;

    private Boolean isSimpleSearch = false;

    private List<Long> idToExcludeList;

    public List<String> getNameTypeExcludeList() {
	if (nameTypeExcludeList == null) {
	    nameTypeExcludeList = new ArrayList<>();
	}
	return nameTypeExcludeList;
    }

    public void setNameTypeExcludeList(List<String> nameTypeExcludeList) {
	this.nameTypeExcludeList = nameTypeExcludeList;
    }

    public List<String> getNameMainIdList() {
	if (nameMainIdList == null) {
	    nameMainIdList = new ArrayList<>();
	}
	return nameMainIdList;
    }

    public void setNameMainIdList(List<String> nameMainIdList) {
	this.nameMainIdList = nameMainIdList;
    }

    public String getWipoLocalId() {
	return wipoLocalId;
    }

    public void setWipoLocalId(String wipoLocalId) {
	this.wipoLocalId = wipoLocalId;
    }

    public String getStatusCode() {
	return statusCode;
    }

    public void setStatusCode(String statusCode) {
	this.statusCode = statusCode;
    }

    public Date getBirthFoundationDate() {
	return birthFoundationDate;
    }

    public void setBirthFoundationDate(Date birthFoundationDate) {
	this.birthFoundationDate = birthFoundationDate;
    }

    public String getGender() {
	return gender;
    }

    public void setGender(String gender) {
	this.gender = gender;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public String getBirthPlace() {
	return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
	this.birthPlace = birthPlace;
    }

    public String getBirthCountryCode() {
	return birthCountryCode;
    }

    public void setBirthCountryCode(String birthCountryCode) {
	this.birthCountryCode = birthCountryCode;
    }

    public String getCitizenship() {
	return citizenship;
    }

    public void setCitizenship(String citizenship) {
	this.citizenship = citizenship;
    }

    public String getLastCompanyName() {
	return lastCompanyName;
    }

    public void setLastCompanyName(String lastCompanyName) {
	this.lastCompanyName = lastCompanyName;
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getNameType() {
	return nameType;
    }

    public void setNameType(String nameType) {
	this.nameType = nameType;
    }

    public String getCmoAcronym() {
	return cmoAcronym;
    }

    public void setCmoAcronym(String cmoAcronym) {
	this.cmoAcronym = cmoAcronym;
    }

    public Date getDateFrom() {
	return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
	this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
	return dateTo;
    }

    public void setDateTo(Date dateTo) {
	this.dateTo = dateTo;
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

    public String getLastName() {
	return getLastCompanyName();
    }

    public void setLastName(String lastName) {
	setLastCompanyName(lastName);
    }

    public String getCmoOfAffiliation() {
	return getCmoAcronym();
    }

    public void setCmoOfAffiliation(String cmoOfAffiliation) {
	setCmoAcronym(cmoOfAffiliation);
    }

    public Date getDateBirth() {
	return getBirthFoundationDate();
    }

    public void setDateBirth(Date dateBirth) {
	setBirthFoundationDate(dateBirth);
    }

    public String getCountryOfBirth() {
	return getBirthCountryCode();
    }

    public void setCountryOfBirth(String countryOfBirth) {
	setBirthCountryCode(countryOfBirth);
    }

    public String getCountryCitizenship() {
	return getCitizenship();
    }

    public void setCountryCitizenship(String citizenship) {
	setCitizenship(citizenship);
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getDomain() {
	return domain;
    }

    public void setDomain(String domain) {
	this.domain = domain;
    }

    public Long getIdInterestedParty() {
	return getId();
    }

    public void setIdInterestedParty(Long idInterestedParty) {
	setId(idInterestedParty);
    }

    public List<String> getCreationClassCodeList() {
	if (null == creationClassCodeList) {
	    creationClassCodeList = new ArrayList<>();
	}
	return creationClassCodeList;
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

    public void setIdToExcludeList(List<Long> idToExcludeList) {
	this.idToExcludeList = idToExcludeList;
    }

    public Boolean getIsSimpleSearch() {
	return isSimpleSearch;
    }

    public void setIsSimpleSearch(Boolean isSimpleSearch) {
	this.isSimpleSearch = isSimpleSearch;
    }

}
