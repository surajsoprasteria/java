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

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Identifiable;

/**
 * The Class AccountIdentifierFlat.
 */
@SuppressWarnings({"squid:S1948"})
public class AccountIdentifierFlat implements Serializable, Identifiable {

	private static final long serialVersionUID = 3020714403017978780L;

	private Long idAccountIdentifier;

	private Long fkAccount;

	private Long fkIdentifier;

	private String code;

	private String value;


	/**
	 * Gets the fk account.
	 *
	 * @return the fk account
	 */
	public Long getFkAccount() {
		return this.fkAccount;
	}

	/**
	 * Gets the fk identifier.
	 *
	 * @return the fk identifier
	 */
	public Long getFkIdentifier() {
		return this.fkIdentifier;
	}


	@Override
	public Long getId() {
		return getIdAccountIdentifier();
	}

	/**
	 * Gets the id account identifier.
	 *
	 * @return the id account identifier
	 */
	public Long getIdAccountIdentifier() {
		return this.idAccountIdentifier;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Sets the fk account.
	 *
	 * @param fkAccount
	 *            the new fk account
	 */
	public void setFkAccount(Long fkAccount) {
		this.fkAccount = fkAccount;
	}

	/**
	 * Sets the fk identifier.
	 *
	 * @param fkIdentifier
	 *            the new fk identifier
	 */
	public void setFkIdentifier(Long fkIdentifier) {
		this.fkIdentifier = fkIdentifier;
	}


	@Override
	public void setId(Long id) {
		setIdAccountIdentifier(id);
	}

	/**
	 * Sets the id account identifier.
	 *
	 * @param idAccountIdentifier
	 *            the new id account identifier
	 */
	public void setIdAccountIdentifier(Long idAccountIdentifier) {
		this.idAccountIdentifier = idAccountIdentifier;
	}

	/**
	 * Sets the value.
	 *
	 * @param value
	 *            the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
