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
 * The Enum RoleTypeEnum.
 */
public enum RoleTypeEnum {

	/** The b. */
	B("B", "roles.type-both"),
	
	/** The n. */
	N("N", "roles.type-natural"),

	/** The l. */
	L("L", "roles.type-legal");

	/** The code. */
	private final String code;

	private final String msgCode;

	/**
	 * Instantiates a new role type enum.
	 *
	 * @param code
	 *            the code
	 * @param description
	 *            the description
	 */
	RoleTypeEnum(String code, String msgCode) {
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