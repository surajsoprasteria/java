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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Creatable;
import org.wipo.connect.common.dto.Identifiable;
import org.wipo.connect.common.dto.Updatable;
import org.wipo.connect.shared.exchange.enumerator.IdentifierTypeEnum;

/**
 * The Class Account.
 *
 * @author Minervini
 */
public class Account extends BaseDTO implements Identifiable, Creatable, Updatable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7785313727406869876L;

    /** The id account. */
    private Long idAccount;

    /** The email. */
    private String email;

    /** The password. */
    private String password;

    /** The type. */
    private String type;

    private String typeCode;

    /** The active. */
    private Boolean active;

    private String name;

    private String firstName;

    /** The account identifier list. */
    private List<AccountIdentifierFlat> accountIdentifierList;

    private List<SecGroup> secGroupList;

    /**
     * Gets the account identifier list.
     *
     * @return the account identifier list
     */
    public List<AccountIdentifierFlat> getAccountIdentifierList() {
	if (this.accountIdentifierList == null) {
	    this.accountIdentifierList = new ArrayList<>();
	}
	return this.accountIdentifierList;
    }

    /**
     * Gets the active.
     *
     * @return the active
     */
    public Boolean getActive() {
	return this.active;
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

    /**
     * Gets the email.
     *
     * @return the email
     */
    public String getEmail() {
	return this.email;
    }

    @Override
    public Long getId() {
	return getIdAccount();
    }

    /**
     * Gets the id account.
     *
     * @return the id account
     */
    public Long getIdAccount() {
	return this.idAccount;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
	return this.password;
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
     * Gets the wcc id.
     *
     * @return the wcc id
     */
    public String getWccId() {
	String wccId = null;
	for (AccountIdentifierFlat wid : getAccountIdentifierList()) {
	    if (wid.getCode().equals(IdentifierTypeEnum.WCC_ACCOUNT_ID.name())) {
		wccId = wid.getValue();
		break;
	    }
	}
	return wccId;
    }

    /**
     * Sets the account identifier list.
     *
     * @param accountIdentifierList
     *            the new account identifier list
     */
    public void setAccountIdentifierList(List<AccountIdentifierFlat> accountIdentifierList) {
	this.accountIdentifierList = accountIdentifierList;
    }

    /**
     * Sets the active.
     *
     * @param active
     *            the new active
     */
    public void setActive(Boolean active) {
	this.active = active;
    }

    /**
     * Sets the email.
     *
     * @param email
     *            the new email
     */
    public void setEmail(String email) {
	this.email = email;
    }

    @Override
    public void setId(Long id) {
	setIdAccount(id);

    }

    /**
     * Sets the id account.
     *
     * @param idAccount
     *            the new id account
     */
    public void setIdAccount(Long idAccount) {
	this.idAccount = idAccount;
    }

    /**
     * Sets the password.
     *
     * @param password
     *            the new password
     */
    public void setPassword(String password) {
	this.password = password;
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
     * Gets the sec group list.
     *
     * @return the sec group list
     */
    public List<SecGroup> getSecGroupList() {
	if (secGroupList == null) {
	    secGroupList = new ArrayList<SecGroup>();
	}
	return secGroupList;
    }

    /**
     * Sets the sec group list.
     *
     * @param secGroupList
     *            the new sec group list
     */
    public void setSecGroupList(List<SecGroup> secGroupList) {
	this.secGroupList = secGroupList;
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
     * Gets the type code.
     *
     * @return the type code
     */
    public String getTypeCode() {
	return typeCode;
    }

}
