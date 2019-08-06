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
 * The Enum InterestedPartyTypeEnum.
 */
public enum TypeEnum {

	/** The n. */
	N("N", "interestedParty.type-natural"),

	/** The l. */
	L("L", "interestedParty.type-legal");

	/** The code. */
	private final String code;

	private final String msgCode;

	/**
	 * Instantiates a new interested party type enum.
	 *
	 * @param code
	 *            the code
	 * @param description
	 *            the description
	 */
	TypeEnum(String code, String msgCode) {
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