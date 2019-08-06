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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Creatable;
import org.wipo.connect.common.dto.Identifiable;
import org.wipo.connect.common.dto.Updatable;
import org.wipo.connect.shared.exchange.enumerator.NameTypeEnum;

public class InterestedParty extends BaseDTO implements Identifiable, Creatable, Updatable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -9198104313603451698L;

    /** The account. */
    private Account account;

    /** The id interested party. */
    private Long idInterestedParty;

    /** The fk status. */
    private Long fkStatus;

    /** The status code. */
    private String statusCode;

    /** The interested party identifier flat list. */
    private List<InterestedPartyIdentifierFlat> interestedPartyIdentifierFlatList;

    /** The birth date. */
    private Date birthDate;

    /** The sex. */
    private String sex;

    /** The marital status. */
    private String maritalStatus;

    /** The birth place. */
    private String birthPlace;

    /** The birth state. */
    private String birthState;

    /** The creation date. */
    private Date creationDate;

    /** The fk birth country. */
    private Long fkBirthCountry;

    /** The birth country code. */
    private String birthCountryCode;

    /** The citizenship code list. */
    private List<String> citizenshipCodeList;

    /** The citizenship id list. */
    private List<Long> citizenshipIdList;

    /** The name list. */
    private List<Name> nameList;

    /** The type. */
    private String type;

    /** The death date. */
    private Date deathDate;

    /** The amendment timestamp. */
    private Date amendmentTimestamp;

    /** The domain. */
    private String domain;

    /** The do affiliation. */
    private Boolean doAffiliation;

    /** The is affiliated. */
    private Boolean isAffiliated;

    /** The affiliation list. */
    private List<Affiliation> affiliationList;

    /** The affiliated cmos. */
    private Set<String> affiliatedCmos;

    private Long fkCmo;

    private String cmoOriginCode;

    private String mainId;

    private List<Long> fkCreationClassList;

    private List<String> creationClassCodeList;

    private Long syncVersion;

    private BigDecimal score;

    public Long getSyncVersion() {
	return syncVersion;
    }

    public void setSyncVersion(Long syncVersion) {
	this.syncVersion = syncVersion;
    }

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
     * Gets the account.
     *
     * @return the account
     */
    public Account getAccount() {
	return this.account;
    }

    /**
     * Gets the amendment timestamp.
     *
     * @return the amendment timestamp
     */
    public Date getAmendmentTimestamp() {
	return this.amendmentTimestamp;
    }

    /**
     * Gets the birth country code.
     *
     * @return the birth country code
     */
    public String getBirthCountryCode() {
	return this.birthCountryCode;
    }

    /**
     * Gets the birth date.
     *
     * @return the birth date
     */
    public Date getBirthDate() {
	return this.birthDate;
    }

    /**
     * Gets the birth place.
     *
     * @return the birth place
     */
    public String getBirthPlace() {
	return this.birthPlace;
    }

    /**
     * Gets the birth state.
     *
     * @return the birth state
     */
    public String getBirthState() {
	return this.birthState;
    }

    /**
     * Gets the citizenship code list.
     *
     * @return the citizenship code list
     */
    public List<String> getCitizenshipCodeList() {
	if (this.citizenshipCodeList == null) {
	    this.citizenshipCodeList = new ArrayList<>();
	}
	return this.citizenshipCodeList;
    }

    /**
     * Gets the citizenship id list.
     *
     * @return the citizenship id list
     */
    public List<Long> getCitizenshipIdList() {
	if (null == this.citizenshipIdList) {
	    this.citizenshipIdList = new ArrayList<>();
	}
	return this.citizenshipIdList;
    }

    /**
     * Gets the creation date.
     *
     * @return the creation date
     */
    public Date getCreationDate() {
	return this.creationDate;
    }

    /**
     * Gets the death date.
     *
     * @return the death date
     */
    public Date getDeathDate() {
	return this.deathDate;
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
     * Gets the fk birth country.
     *
     * @return the fk birth country
     */
    public Long getFkBirthCountry() {
	return this.fkBirthCountry;
    }

    /**
     * Gets the fk status.
     *
     * @return the fk status
     */
    public Long getFkStatus() {
	return this.fkStatus;
    }

    @Override
    public Long getId() {
	return getIdInterestedParty();
    }

    /**
     * Gets the id interested party.
     *
     * @return the id interested party
     */
    public Long getIdInterestedParty() {
	return this.idInterestedParty;
    }

    /**
     * Gets the interested party identifier flat list.
     *
     * @return the interested party identifier flat list
     */
    public List<InterestedPartyIdentifierFlat> getInterestedPartyIdentifierFlatList() {
	if (this.interestedPartyIdentifierFlatList == null) {
	    this.interestedPartyIdentifierFlatList = new ArrayList<>();
	}
	return this.interestedPartyIdentifierFlatList;
    }

    /**
     * Gets the marital status.
     *
     * @return the marital status
     */
    public String getMaritalStatus() {
	return this.maritalStatus;
    }

    /**
     * Gets the name list.
     *
     * @return the name list
     */
    public List<Name> getNameList() {
	if (this.nameList == null) {
	    this.nameList = new ArrayList<>();
	}
	return this.nameList;
    }

    /**
     * Gets the patronym name.
     *
     * @return the patronym name
     */
    public String getPatronymName() {
	String paName = null;
	for (Name item : getNameList()) {
	    if (item.getNameType() != null && item.getNameType().equalsIgnoreCase(NameTypeEnum.PA.name())) {
		paName = item.getFullName();
		break;
	    }
	}
	return paName;
    }

    public String getPaMainId() {
	String paMainId = null;
	for (Name item : getNameList()) {
	    if (item.getNameType() != null && item.getNameType().equalsIgnoreCase(NameTypeEnum.PA.name())) {
		paMainId = item.getMainId();
		break;
	    }
	}
	return paMainId;
    }

    /**
     * Gets the sex.
     *
     * @return the sex
     */
    public String getSex() {
	return this.sex;
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
     * Gets the type.
     *
     * @return the type
     */
    public String getType() {
	return this.type;
    }

    /**
     * Sets the account.
     *
     * @param account
     *            the new account
     */
    public void setAccount(Account account) {
	this.account = account;
    }

    /**
     * Sets the amendment timestamp.
     *
     * @param amendmentTimestamp
     *            the new amendment timestamp
     */
    public void setAmendmentTimestamp(Date amendmentTimestamp) {
	this.amendmentTimestamp = amendmentTimestamp;
    }

    /**
     * Sets the birth country code.
     *
     * @param birthCountryCode
     *            the new birth country code
     */
    public void setBirthCountryCode(String birthCountryCode) {
	this.birthCountryCode = birthCountryCode;
    }

    /**
     * Sets the birth date.
     *
     * @param birthDate
     *            the new birth date
     */
    public void setBirthDate(Date birthDate) {
	this.birthDate = birthDate;
    }

    /**
     * Sets the birth place.
     *
     * @param birthPlace
     *            the new birth place
     */
    public void setBirthPlace(String birthPlace) {
	this.birthPlace = birthPlace;
    }

    /**
     * Sets the birth state.
     *
     * @param birthState
     *            the new birth state
     */
    public void setBirthState(String birthState) {
	this.birthState = birthState;
    }

    /**
     * Sets the citizenship code list.
     *
     * @param citizenshipCodeList
     *            the new citizenship code list
     */
    public void setCitizenshipCodeList(List<String> citizenshipCodeList) {
	this.citizenshipCodeList = citizenshipCodeList;
    }

    /**
     * Sets the citizenship id list.
     *
     * @param citizenshipIdList
     *            the new citizenship id list
     */
    public void setCitizenshipIdList(List<Long> citizenshipIdList) {
	this.citizenshipIdList = citizenshipIdList;
    }

    /**
     * Sets the creation date.
     *
     * @param creationDate
     *            the new creation date
     */
    public void setCreationDate(Date creationDate) {
	this.creationDate = creationDate;
    }

    /**
     * Sets the death date.
     *
     * @param deathDate
     *            the new death date
     */
    public void setDeathDate(Date deathDate) {
	this.deathDate = deathDate;
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

    /**
     * Sets the fk birth country.
     *
     * @param fkBirthCountry
     *            the new fk birth country
     */
    public void setFkBirthCountry(Long fkBirthCountry) {
	this.fkBirthCountry = fkBirthCountry;
    }

    /**
     * Sets the fk status.
     *
     * @param fkStatus
     *            the new fk status
     */
    public void setFkStatus(Long fkStatus) {
	this.fkStatus = fkStatus;
    }

    @Override
    public void setId(Long id) {
	setIdInterestedParty(id);
    }

    /**
     * Sets the id interested party.
     *
     * @param idInterestedParty
     *            the new id interested party
     */
    public void setIdInterestedParty(Long idInterestedParty) {
	this.idInterestedParty = idInterestedParty;
    }

    /**
     * Sets the interested party identifier flat list.
     *
     * @param interestedPartyIdentifierFlatList
     *            the new interested party identifier flat list
     */
    public void setInterestedPartyIdentifierFlatList(List<InterestedPartyIdentifierFlat> interestedPartyIdentifierFlatList) {
	this.interestedPartyIdentifierFlatList = interestedPartyIdentifierFlatList;
    }

    /**
     * Sets the marital status.
     *
     * @param maritalStatus
     *            the new marital status
     */
    public void setMaritalStatus(String maritalStatus) {
	this.maritalStatus = maritalStatus;
    }

    /**
     * Sets the name list.
     *
     * @param nameList
     *            the new name list
     */
    public void setNameList(List<Name> nameList) {
	this.nameList = nameList;
    }

    /**
     * Sets the sex.
     *
     * @param sex
     *            the new sex
     */
    public void setSex(String sex) {
	this.sex = sex;
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
     * Sets the type.
     *
     * @param type
     *            the new type
     */
    public void setType(String type) {
	this.type = type;
    }

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
    }

    /**
     * Get the affiliation list.
     *
     * @return Affiliation list
     */
    public List<Affiliation> getAffiliationList() {
	if (this.affiliationList == null) {
	    affiliationList = new ArrayList<>();
	}
	return affiliationList;
    }

    /**
     * Set the affiliation list.
     *
     * @param affiliationList
     *            the new affiliation list
     */
    public void setAffiliationList(List<Affiliation> affiliationList) {
	this.affiliationList = affiliationList;
    }

    /**
     * Gets the do affiliation.
     *
     * @return the do affiliation
     */
    public Boolean getDoAffiliation() {
	return doAffiliation;
    }

    /**
     * Sets the do affiliation.
     *
     * @param doAffiliation
     *            the new do affiliation
     */
    public void setDoAffiliation(Boolean doAffiliation) {
	this.doAffiliation = doAffiliation;
    }

    /**
     * Gets the checks if is affiliated.
     *
     * @return the checks if is affiliated
     */
    public Boolean getIsAffiliated() {
	return isAffiliated;
    }

    /**
     * Sets the checks if is affiliated.
     *
     * @param isAffiliated
     *            the new checks if is affiliated
     */
    public void setIsAffiliated(Boolean isAffiliated) {
	this.isAffiliated = isAffiliated;
    }

    /**
     * Gets the affiliated cmos.
     *
     * @return the affiliated cmos
     */
    public Set<String> getAffiliatedCmos() {
	if (affiliatedCmos == null) {
	    affiliatedCmos = new HashSet<>();
	}
	return affiliatedCmos;
    }

    /**
     * Sets the affiliated cmos.
     *
     * @param affiliatedCmos
     *            the new affiliated cmos
     */
    public void setAffiliatedCmos(Set<String> affiliatedCmos) {
	this.affiliatedCmos = affiliatedCmos;
    }

    /**
     * Gets the pa name.
     *
     * @return the pa name
     */
    public String getPaName() {
	String paName = null;
	for (Name item : getNameList()) {
	    if (item.getNameType() != null && item.getNameType().equalsIgnoreCase(NameTypeEnum.PA.name())) {
		paName = item.getName();
		break;
	    }
	}
	return paName;
    }

    /**
     * Gets the pa first name.
     *
     * @return the pa first name
     */
    public String getPaFirstName() {
	String paName = null;
	for (Name item : getNameList()) {
	    if (item.getNameType() != null && item.getNameType().equalsIgnoreCase(NameTypeEnum.PA.name())) {
		paName = item.getFirstName();
		break;
	    }
	}
	return paName;
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

    public List<Long> getFkCreationClassList() {
	return fkCreationClassList;
    }

    public void setFkCreationClassList(List<Long> fkCreationClassList) {
	this.fkCreationClassList = fkCreationClassList;
    }

    public List<String> getCreationClassCodeList() {
	return creationClassCodeList;
    }

    public void setCreationClassCodeList(List<String> creationClassCodeList) {
	this.creationClassCodeList = creationClassCodeList;
    }

    public List<Name> getGroupName(List<String> groupNameTypeList) {
	List<Name> groupNameList = new ArrayList<>();
	for (Name item : getNameList()) {
	    for (String groupType : groupNameTypeList) {
		if (item.getNameType() != null && item.getNameType().equalsIgnoreCase(groupType)) {
		    groupNameList.add(item);
		}
	    }
	}
	return groupNameList;
    }

    public BigDecimal getScore() {
	return score;
    }

    public void setScore(BigDecimal score) {
	this.score = score;
    }
}
