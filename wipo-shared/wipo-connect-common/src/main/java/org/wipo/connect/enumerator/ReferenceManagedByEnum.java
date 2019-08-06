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

package org.wipo.connect.enumerator;

/**
 * The Enum ReferenceManagedByEnum.
 */
public enum ReferenceManagedByEnum {

	
	LOCAL("LOCAL", "reference.type-local"),
	
	SHARED("SHARED", "reference.type-shared"),
	
	SYSTEM("SYSTEM", "reference.type-system");

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
	ReferenceManagedByEnum(String code, String msgCode) {
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