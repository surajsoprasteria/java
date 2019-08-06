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
package org.wipo.connect.common.import_model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * The Class IpRow.
 */
public class IpRow extends BaseIpRow {

    private static final long serialVersionUID = -1105312053552705928L;

    private String idField;
    private String rowType;
    private String transaction;
    // Ip row field//
    private String mainId;
    private String type;
    private List<String> citizenshipList;
    private String sex;
    private String birthDate;
    private String deathDate;
    private String countryOfBirth;
    private String birthPlace;// TODO: WCONNECT-63 ------------------------------
    // Name row field//
    private String nameType;
    private String lastCompanyName;
    private String firstName;
    // Affiliation row field//
    private String creationClass;
    private String rightType;
    private String role;
    private String affiliationFrom;
    private String affiliationTo;
    private String signatureDate;
    private String share;
    private String territory;
    private String cmo;
    // Contact field
    private String value;
    // Address field
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String addressCity;
    private String addressProvince;
    private String addressZipCode;
    private String addressCountry;
    // Bank field
    private String bankName;
    private String branch;
    private String address;
    private String accountName;
    private String swift;
    private String accountNumber;
    private String typeOfPayment;
    private List<String> tags;
    private String dummyInfo;

    public String getIdField() {
	return idField;
    }

    public void setIdField(String idField) {
	this.idField = idField;
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

    public String getType() {
	return type;
    }

    public void setType(String type) {
	this.type = type;
    }

    public List<String> getCitizenshipList() {
	if (citizenshipList == null) {
	    citizenshipList = new ArrayList<>();
	}
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

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
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

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
    }

    public List<String> getTags() {
	if (tags == null) {
	    tags = new ArrayList<>();
	}
	return tags;
    }

    public void setTags(List<String> tags) {
	this.tags = tags;
    }

    public String getMainId() {
	return mainId;
    }

    public void setMainId(String mainId) {
	this.mainId = mainId;
    }

    public String getDummyInfo() {
	return dummyInfo;
    }

    public void setDummyInfo(String dummyInfo) {
	this.dummyInfo = dummyInfo;
    }

    public String getBirthPlace() {
	return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
	this.birthPlace = birthPlace;
    }

}
