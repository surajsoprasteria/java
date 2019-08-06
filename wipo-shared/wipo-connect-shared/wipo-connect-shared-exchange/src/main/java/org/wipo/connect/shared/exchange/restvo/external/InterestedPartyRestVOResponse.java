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

import org.wipo.connect.shared.exchange.dto.impl.SecGroup;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class InterestedParty.
 *
 * @author minervini
 */
@JsonInclude(value = Include.NON_EMPTY)
public class InterestedPartyRestVOResponse implements Serializable {

    private static final long serialVersionUID = -7851405732040232961L;

    /** The status code. */
    private String statusCode;

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

    /** The birth country code. */
    private String birthCountryCode;

    /** The citizenship code list. */
    private List<String> citizenshipCodeList;

    /** The type. */
    private String type;

    /** The death date. */
    private Date deathDate;

    /** The amendment timestamp. */
    private Date amendmentTimestamp;

    /** The domain. */
    private String domain;

    /** The is affiliated. */
    private Boolean isAffiliated;

    /** The affiliated cmos. */
    private Set<String> affiliatedCmos;

    private String cmoOriginCode;

    private String mainId;

    private List<String> creationClassCodeList;

    private Long syncVersion;

    private BigDecimal score;

    private AccountVOResponse account;
    private List<InterestedPartyIdentifierVOResponse> interestedPartyIdentifierList;
    private List<NameRestVOResponse> nameList;
    private List<AffiliationVOResponse> affiliationList;

    @JsonInclude(value = Include.NON_EMPTY)
    public static class AccountVOResponse implements Serializable {

	private static final long serialVersionUID = -2115338859083297527L;

	private String email;

	private String password;

	private String type;

	private String typeCode;

	private Boolean active;

	private String name;

	private String firstName;

	private List<AccountIdentifierVOResponse> accountIdentifierList;

	private List<SecGroup> secGroupList;

	public String getEmail() {
	    return email;
	}

	public void setEmail(String email) {
	    this.email = email;
	}

	public String getPassword() {
	    return password;
	}

	public void setPassword(String password) {
	    this.password = password;
	}

	public String getType() {
	    return type;
	}

	public void setType(String type) {
	    this.type = type;
	}

	public String getTypeCode() {
	    return typeCode;
	}

	public void setTypeCode(String typeCode) {
	    this.typeCode = typeCode;
	}

	public Boolean getActive() {
	    return active;
	}

	public void setActive(Boolean active) {
	    this.active = active;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public String getFirstName() {
	    return firstName;
	}

	public void setFirstName(String firstName) {
	    this.firstName = firstName;
	}

	public List<AccountIdentifierVOResponse> getAccountIdentifierList() {
	    return accountIdentifierList;
	}

	public void setAccountIdentifierList(List<AccountIdentifierVOResponse> accountIdentifierList) {
	    this.accountIdentifierList = accountIdentifierList;
	}

	public List<SecGroup> getSecGroupList() {
	    return secGroupList;
	}

	public void setSecGroupList(List<SecGroup> secGroupList) {
	    this.secGroupList = secGroupList;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class AccountIdentifierVOResponse implements Serializable {

	private static final long serialVersionUID = 4587135389069298358L;

	private String code;

	private String value;

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

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class SecGroupVOResponse implements Serializable {

	private static final long serialVersionUID = -1560172139875317663L;

	private String code;

	private String description;

	private String note;

	private List<PermissionVOResponse> permissionList;

	public String getCode() {
	    return code;
	}

	public void setCode(String code) {
	    this.code = code;
	}

	public String getDescription() {
	    return description;
	}

	public void setDescription(String description) {
	    this.description = description;
	}

	public String getNote() {
	    return note;
	}

	public void setNote(String note) {
	    this.note = note;
	}

	public List<PermissionVOResponse> getPermissionList() {
	    return permissionList;
	}

	public void setPermissionList(List<PermissionVOResponse> permissionList) {
	    this.permissionList = permissionList;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class PermissionVOResponse implements Serializable {

	private static final long serialVersionUID = -1787580069844844394L;
	private String code;
	private String descriptionCode;
	private String descriptionPerm;

	public String getCode() {
	    return code;
	}

	public void setCode(String code) {
	    this.code = code;
	}

	public String getDescriptionCode() {
	    return descriptionCode;
	}

	public void setDescriptionCode(String descriptionCode) {
	    this.descriptionCode = descriptionCode;
	}

	public String getDescriptionPerm() {
	    return descriptionPerm;
	}

	public void setDescriptionPerm(String descriptionPerm) {
	    this.descriptionPerm = descriptionPerm;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class InterestedPartyIdentifierVOResponse implements Serializable {
	private static final long serialVersionUID = 8799243874586068259L;

	private String code;

	private String value;

	public String getCode() {
	    return this.code;
	}

	public String getValue() {
	    return this.value;
	}

	public void setCode(String code) {
	    this.code = code;
	}

	public void setValue(String value) {
	    this.value = value;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class AffiliationVOResponse implements Serializable {

	private static final long serialVersionUID = 708645530579031068L;

	private CmoVOResponse cmo;

	private Date startDate;

	private Date endDate;

	private BigDecimal shareValue;

	private Date signatureDate;

	private Date amendmentTimestamp;

	private List<AffiliationDomainVOResponse> affiliationDomainList;

	private List<String> territoryCodeList;

	private String territoryFormula;

	public CmoVOResponse getCmo() {
	    return cmo;
	}

	public void setCmo(CmoVOResponse cmo) {
	    this.cmo = cmo;
	}

	public Date getStartDate() {
	    return startDate;
	}

	public void setStartDate(Date startDate) {
	    this.startDate = startDate;
	}

	public Date getEndDate() {
	    return endDate;
	}

	public void setEndDate(Date endDate) {
	    this.endDate = endDate;
	}

	public BigDecimal getShareValue() {
	    return shareValue;
	}

	public void setShareValue(BigDecimal shareValue) {
	    this.shareValue = shareValue;
	}

	public Date getSignatureDate() {
	    return signatureDate;
	}

	public void setSignatureDate(Date signatureDate) {
	    this.signatureDate = signatureDate;
	}

	public Date getAmendmentTimestamp() {
	    return amendmentTimestamp;
	}

	public void setAmendmentTimestamp(Date amendmentTimestamp) {
	    this.amendmentTimestamp = amendmentTimestamp;
	}

	public List<AffiliationDomainVOResponse> getAffiliationDomainList() {
	    return affiliationDomainList;
	}

	public void setAffiliationDomainList(List<AffiliationDomainVOResponse> affiliationDomainList) {
	    this.affiliationDomainList = affiliationDomainList;
	}

	public List<String> getTerritoryCodeList() {
	    return territoryCodeList;
	}

	public void setTerritoryCodeList(List<String> territoryCodeList) {
	    this.territoryCodeList = territoryCodeList;
	}

	public String getTerritoryFormula() {
	    return territoryFormula;
	}

	public void setTerritoryFormula(String territoryFormula) {
	    this.territoryFormula = territoryFormula;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class CmoVOResponse implements Serializable {

	private static final long serialVersionUID = 872273304565650734L;

	private String name;

	private String code;

	private String acronym;

	private String contact;

	private String description;

	private String internationalName;

	private List<CmoDomainVOResponse> cmoDomainList;

	private Boolean isDataSource;

	// TODO: to remap
	private String originCountry;
	// TODO: to remap
	private Long fkType;

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public String getCode() {
	    return code;
	}

	public void setCode(String code) {
	    this.code = code;
	}

	public String getAcronym() {
	    return acronym;
	}

	public void setAcronym(String acronym) {
	    this.acronym = acronym;
	}

	public String getContact() {
	    return contact;
	}

	public void setContact(String contact) {
	    this.contact = contact;
	}

	public String getDescription() {
	    return description;
	}

	public void setDescription(String description) {
	    this.description = description;
	}

	public String getInternationalName() {
	    return internationalName;
	}

	public void setInternationalName(String internationalName) {
	    this.internationalName = internationalName;
	}

	public List<CmoDomainVOResponse> getCmoDomainList() {
	    return cmoDomainList;
	}

	public void setCmoDomainList(List<CmoDomainVOResponse> cmoDomainList) {
	    this.cmoDomainList = cmoDomainList;
	}

	public Boolean getIsDataSource() {
	    return isDataSource;
	}

	public void setIsDataSource(Boolean isDataSource) {
	    this.isDataSource = isDataSource;
	}

	public String getOriginCountry() {
	    return originCountry;
	}

	public void setOriginCountry(String originCountry) {
	    this.originCountry = originCountry;
	}

	public Long getFkType() {
	    return fkType;
	}

	public void setFkType(Long fkType) {
	    this.fkType = fkType;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class CmoDomainVOResponse implements Serializable {

	private static final long serialVersionUID = -5277625564584490414L;

	private String ipiRole;

	private String creationClass;

	private Long ipiRightType;

	public String getIpiRole() {
	    return ipiRole;
	}

	public void setIpiRole(String ipiRole) {
	    this.ipiRole = ipiRole;
	}

	public String getCreationClass() {
	    return creationClass;
	}

	public void setCreationClass(String creationClass) {
	    this.creationClass = creationClass;
	}

	public Long getIpiRightType() {
	    return ipiRightType;
	}

	public void setIpiRightType(Long ipiRightType) {
	    this.ipiRightType = ipiRightType;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class AffiliationDomainVOResponse implements Serializable {

	private static final long serialVersionUID = 4478165274653804840L;

	private Boolean isExcluded;

	private String ipiRightTypeCode;

	private String creationClassCode;

	private String ipiRoleCode;

	public Boolean getIsExcluded() {
	    return isExcluded;
	}

	public void setIsExcluded(Boolean isExcluded) {
	    this.isExcluded = isExcluded;
	}

	public String getIpiRightTypeCode() {
	    return ipiRightTypeCode;
	}

	public void setIpiRightTypeCode(String ipiRightTypeCode) {
	    this.ipiRightTypeCode = ipiRightTypeCode;
	}

	public String getCreationClassCode() {
	    return creationClassCode;
	}

	public void setCreationClassCode(String creationClassCode) {
	    this.creationClassCode = creationClassCode;
	}

	public String getIpiRoleCode() {
	    return ipiRoleCode;
	}

	public void setIpiRoleCode(String ipiRoleCode) {
	    this.ipiRoleCode = ipiRoleCode;
	}

    }

    public String getStatusCode() {
	return statusCode;
    }

    public void setStatusCode(String statusCode) {
	this.statusCode = statusCode;
    }

    public Date getBirthDate() {
	return birthDate;
    }

    public void setBirthDate(Date birthDate) {
	this.birthDate = birthDate;
    }

    public String getSex() {
	return sex;
    }

    public void setSex(String sex) {
	this.sex = sex;
    }

    public String getMaritalStatus() {
	return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
	this.maritalStatus = maritalStatus;
    }

    public String getBirthPlace() {
	return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
	this.birthPlace = birthPlace;
    }

    public String getBirthState() {
	return birthState;
    }

    public void setBirthState(String birthState) {
	this.birthState = birthState;
    }

    public Date getCreationDate() {
	return creationDate;
    }

    public void setCreationDate(Date creationDate) {
	this.creationDate = creationDate;
    }

    public String getBirthCountryCode() {
	return birthCountryCode;
    }

    public void setBirthCountryCode(String birthCountryCode) {
	this.birthCountryCode = birthCountryCode;
    }

    public List<String> getCitizenshipCodeList() {
	return citizenshipCodeList;
    }

    public void setCitizenshipCodeList(List<String> citizenshipCodeList) {
	this.citizenshipCodeList = citizenshipCodeList;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public Date getDeathDate() {
	return deathDate;
    }

    public void setDeathDate(Date deathDate) {
	this.deathDate = deathDate;
    }

    public Date getAmendmentTimestamp() {
	return amendmentTimestamp;
    }

    public void setAmendmentTimestamp(Date amendmentTimestamp) {
	this.amendmentTimestamp = amendmentTimestamp;
    }

    public String getDomain() {
	return domain;
    }

    public void setDomain(String domain) {
	this.domain = domain;
    }

    public Boolean getIsAffiliated() {
	return isAffiliated;
    }

    public void setIsAffiliated(Boolean isAffiliated) {
	this.isAffiliated = isAffiliated;
    }

    public Set<String> getAffiliatedCmos() {
	return affiliatedCmos;
    }

    public void setAffiliatedCmos(Set<String> affiliatedCmos) {
	this.affiliatedCmos = affiliatedCmos;
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

    public List<String> getCreationClassCodeList() {
	return creationClassCodeList;
    }

    public void setCreationClassCodeList(List<String> creationClassCodeList) {
	this.creationClassCodeList = creationClassCodeList;
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

    public AccountVOResponse getAccount() {
	return account;
    }

    public void setAccount(AccountVOResponse account) {
	this.account = account;
    }

    public List<InterestedPartyIdentifierVOResponse> getInterestedPartyIdentifierList() {
	return interestedPartyIdentifierList;
    }

    public void setInterestedPartyIdentifierList(List<InterestedPartyIdentifierVOResponse> interestedPartyIdentifierList) {
	this.interestedPartyIdentifierList = interestedPartyIdentifierList;
    }

    public List<NameRestVOResponse> getNameList() {
	return nameList;
    }

    public void setNameList(List<NameRestVOResponse> nameList) {
	this.nameList = nameList;
    }

    public List<AffiliationVOResponse> getAffiliationList() {
	return affiliationList;
    }

    public void setAffiliationList(List<AffiliationVOResponse> affiliationList) {
	this.affiliationList = affiliationList;
    }

}