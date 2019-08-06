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

package org.wipo.connect.shared.exchange.enumerator;

/**
 * The Enum IdentifierLinkedEntityEnum.
 */
public enum IdentifierLinkedEntityEnum {

	/** The i. */
	I("I", "identifiers.type-ip"),
	
	/** The w. */
	W("W", "identifiers.type-work");

	/** The code. */
	private final String code;

	private final String msgCode;

	/**
	 * Instantiates a new Identifier Linked Entity Enum.
	 *
	 * @param code
	 *            the code
	 * @param description
	 *            the description
	 */
	IdentifierLinkedEntityEnum(String code, String msgCode) {
		this.code = code;
		this.msgCode = msgCode;
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
	 * Gets the msg code.
	 *
	 * @return the msg code
	 */
	public String getMsgCode() {
		return msgCode;
	}

}