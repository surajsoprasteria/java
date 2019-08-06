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
package org.wipo.connect.shared.exchange.enumerator;

/**
 * The Enum AgreementTypeEnum.
 */
public enum AgreementTypeEnum {

	/**
	 * The ge.
	 */
	GE("General Agreement"),

	/**
	 * The sp.
	 */
	SP("Specific Agreement"),

	/**
	 * The sp.
	 */
	IM("Implied Agreement");

	private final String value;

	/**
	 * Value.
	 *
	 * @return the string
	 */
	public String value() {
		return value;
	}

	private AgreementTypeEnum(String value) {
		this.value = value;
	}

}
