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


public enum WorkTypeEnum {

	INTERNATIONAL(false, "International", "I"),
	DOMESTIC(true, "Domestic", "D");

	private Boolean value;
	private String label;
	private String code;

	private WorkTypeEnum(Boolean value, String label, String code) {
		this.value = value;
		this.label = label;
		this.code = code;
	}

	public Boolean getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	public String getCode() {
		return code;
	}
	
}
