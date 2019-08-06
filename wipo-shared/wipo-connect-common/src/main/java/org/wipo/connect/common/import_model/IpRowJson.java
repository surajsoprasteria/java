/*
 * Copyright (C) 2015 World Intellectual Property Organization (WIPO).
 * All Rights Reserved.
 *
 * This file is part of WIPO Copyright Connection (WCC).
 *
 *
 * @author Fincons
 *
 */
package org.wipo.connect.common.import_model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(value = Include.NON_EMPTY)
public class IpRowJson implements Serializable {

    private static final long serialVersionUID = 8922216844931002253L;

    private String id;
    private String rowType;
    private String transaction;
    // MAIN ROW
    private String mainId;
    private String type;
    private List<String> citizenshipList;
    private String sex;
    private String birthDate;
    private String deathDate;
    private String countryOfBirth;
    private List<String> tags;

    // NAME ROW
    @JsonProperty("names")
    private List<NameRow> nameRows;
    // AFFILIATION ROW
    @JsonProperty("affiliations")
    private List<AffiliationRow> affiliationRows;
    // IDENTIFIER ROW
    @JsonProperty("identifiers")
    private List<IdentifierRow> identifierRows;
    // DYNAMICFIELD ROW
    @JsonProperty("dynamicFields")
    private List<DynamicFieldRow> dynamicFieldRows;
    // CONTACT ROW
    @JsonProperty("contacts")
    private List<ContactRow> contactRows;
    // ADDRESS ROW
    @JsonProperty("addresses")
    private List<AddressRow> addressRows;
    // BANK ACCOUNT ROW
    @JsonProperty("bankAccounts")
    private List<BankAccountRow> bankAccountRows;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getRowType() {
	return rowType;
    }

    public void setRowType(String rowType) {
	this.rowType = rowType;
    }

    public String getTransaction() {
	return transaction;
    }

    public void setTransaction(String transaction) {
	this.transaction = transaction;
    }

    public List<String> getTags() {
	return tags;
    }

    public void setTags(List<String> tags) {
	this.tags = tags;
    }

    public List<IdentifierRow> getIdentifierRows() {
	if (identifierRows == null) {
	    identifierRows = new ArrayList<>();
	}
	return identifierRows;
    }

    public void setIdentifierRows(List<IdentifierRow> identifierRows) {
	this.identifierRows = identifierRows;
    }

    public List<DynamicFieldRow> getDynamicFieldRows() {
	if (dynamicFieldRows == null) {
	    dynamicFieldRows = new ArrayList<>();
	}
	return dynamicFieldRows;
    }

    public void setDynamicFieldRows(List<DynamicFieldRow> dynamicFieldRows) {
	this.dynamicFieldRows = dynamicFieldRows;
    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class NameRow {
	private String nameType;
	private String lastCompanyName;
	private String firstName;
	private String mainId;

	public NameRow() {
	}

	public String getNameType() {
	    return nameType;
	}

	public void setNameType(String nameType) {
	    this.nameType = nameType;
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

	public String getMainId() {
	    return mainId;
	}

	public void setMainId(String mainId) {
	    this.mainId = mainId;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class IdentifierRow {

	public IdentifierRow() {
	}

	private String type;
	private String value;

	public String getValue() {
	    return value;
	}

	public void setValue(String value) {
	    this.value = value;
	}

	public String getType() {
	    return type;
	}

	public void setType(String type) {
	    this.type = type;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class AffiliationRow {

	private String creationClass;
	private String rightType;
	private String role;
	private String affiliationFrom;
	private String affiliationTo;
	private String signatureDate;
	private String share;
	private String territory;
	private String cmo;

	public AffiliationRow() {
	}

	public String getCreationClass() {
	    return creationClass;
	}

	public void setCreationClass(String creationClass) {
	    this.creationClass = creationClass;
	}

	public String getRightType() {
	    return rightType;
	}

	public void setRightType(String rightType) {
	    this.rightType = rightType;
	}

	public String getRole() {
	    return role;
	}

	public void setRole(String role) {
	    this.role = role;
	}

	public String getAffiliationFrom() {
	    return affiliationFrom;
	}

	public void setAffiliationFrom(String affiliationFrom) {
	    this.affiliationFrom = affiliationFrom;
	}

	public String getAffiliationTo() {
	    return affiliationTo;
	}

	public void setAffiliationTo(String affiliationTo) {
	    this.affiliationTo = affiliationTo;
	}

	public String getSignatureDate() {
	    return signatureDate;
	}

	public void setSignatureDate(String signatureDate) {
	    this.signatureDate = signatureDate;
	}

	public String getShare() {
	    return share;
	}

	public void setShare(String share) {
	    this.share = share;
	}

	public String getTerritory() {
	    return territory;
	}

	public void setTerritory(String territory) {
	    this.territory = territory;
	}

	public String getCmo() {
	    return cmo;
	}

	public void setCmo(String cmo) {
	    this.cmo = cmo;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class DynamicFieldRow {
	private String type;
	private String value;

	public DynamicFieldRow() {
	}

	public String getValue() {
	    return value;
	}

	public void setValue(String value) {
	    this.value = value;
	}

	public String getType() {
	    return type;
	}

	public void setType(String type) {
	    this.type = type;
	}
    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class ContactRow {
	private String type;
	private String value;

	public ContactRow() {
	}

	public String getType() {
	    return type;
	}

	public void setType(String type) {
	    this.type = type;
	}

	public String getValue() {
	    return value;
	}

	public void setValue(String value) {
	    this.value = value;
	}
    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class AddressRow {
	private String type;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String addressCity;
	private String addressProvince;
	private String addressZipCode;
	private String addressCountry;

	public AddressRow() {
	}

	public String getAddressLine1() {
	    return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
	    this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
	    return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
	    this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
	    return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
	    this.addressLine3 = addressLine3;
	}

	public String getAddressCity() {
	    return addressCity;
	}

	public void setAddressCity(String addressCity) {
	    this.addressCity = addressCity;
	}

	public String getAddressProvince() {
	    return addressProvince;
	}

	public void setAddressProvince(String addressProvince) {
	    this.addressProvince = addressProvince;
	}

	public String getAddressZipCode() {
	    return addressZipCode;
	}

	public void setAddressZipCode(String addressZipCode) {
	    this.addressZipCode = addressZipCode;
	}

	public String getAddressCountry() {
	    return addressCountry;
	}

	public void setAddressCountry(String addressCountry) {
	    this.addressCountry = addressCountry;
	}

	public String getType() {
	    return type;
	}

	public void setType(String type) {
	    this.type = type;
	}

    }

    @JsonInclude(value = Include.NON_EMPTY)
    public static class BankAccountRow {
	private String bankName;
	private String branch;
	private String address;
	private String accountName;
	private String swift;
	private String accountNumber;
	private String typeOfPayment;

	public BankAccountRow() {
	}

	public String getBankName() {
	    return bankName;
	}

	public void setBankName(String bankName) {
	    this.bankName = bankName;
	}

	public String getBranch() {
	    return branch;
	}

	public void setBranch(String branch) {
	    this.branch = branch;
	}

	public String getAddress() {
	    return address;
	}

	public void setAddress(String address) {
	    this.address = address;
	}

	public String getAccountName() {
	    return accountName;
	}

	public void setAccountName(String accountName) {
	    this.accountName = accountName;
	}

	public String getSwift() {
	    return swift;
	}

	public void setSwift(String swift) {
	    this.swift = swift;
	}

	public String getAccountNumber() {
	    return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
	    this.accountNumber = accountNumber;
	}

	public String getTypeOfPayment() {
	    return typeOfPayment;
	}

	public void setTypeOfPayment(String typeOfPayment) {
	    this.typeOfPayment = typeOfPayment;
	}

    }

    public String getMainId() {
	return mainId;
    }

    public void setMainId(String mainId) {
	this.mainId = mainId;
    }

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public List<String> getCitizenshipList() {
	return citizenshipList;
    }

    public void setCitizenshipList(List<String> citizenshipList) {
	this.citizenshipList = citizenshipList;
    }

    public String getSex() {
	return sex;
    }

    public void setSex(String sex) {
	this.sex = sex;
    }

    public String getBirthDate() {
	return birthDate;
    }

    public void setBirthDate(String birthDate) {
	this.birthDate = birthDate;
    }

    public String getDeathDate() {
	return deathDate;
    }

    public void setDeathDate(String deathDate) {
	this.deathDate = deathDate;
    }

    public String getCountryOfBirth() {
	return countryOfBirth;
    }

    public void setCountryOfBirth(String countryOfBirth) {
	this.countryOfBirth = countryOfBirth;
    }

    public List<NameRow> getNameRows() {
	if (nameRows == null) {
	    nameRows = new ArrayList<>();
	}
	return nameRows;
    }

    public void setNameRows(List<NameRow> nameRows) {
	this.nameRows = nameRows;
    }

    public List<AffiliationRow> getAffiliationRows() {
	if (affiliationRows == null) {
	    affiliationRows = new ArrayList<>();
	}
	return affiliationRows;
    }

    public void setAffiliationRows(List<AffiliationRow> affiliationRows) {
	this.affiliationRows = affiliationRows;
    }

    public List<AddressRow> getAddressRows() {
	if (addressRows == null) {
	    addressRows = new ArrayList<>();
	}
	return addressRows;
    }

    public void setAddressRows(List<AddressRow> addressRows) {
	this.addressRows = addressRows;
    }

    public List<BankAccountRow> getBankAccountRows() {
	if (bankAccountRows == null) {
	    bankAccountRows = new ArrayList<>();
	}
	return bankAccountRows;
    }

    public void setBankAccountRows(List<BankAccountRow> bankAccountRows) {
	this.bankAccountRows = bankAccountRows;
    }

    public List<ContactRow> getContactRows() {
	if (contactRows == null) {
	    contactRows = new ArrayList<>();
	}
	return contactRows;
    }

    public void setContactRows(List<ContactRow> contactRows) {
	this.contactRows = contactRows;
    }

}
