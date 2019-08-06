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



public enum CurrencyPositionEnum {

	BEFORE("BEFORE","global.currency-pos-before"),
	AFTER("AFTER","global.currency-pos-after");

	private final String code;
	private final String msgCode;

	CurrencyPositionEnum(String code, String msgCode){
		this.code = code;
		this.msgCode = msgCode;
	}

	public String getCode() {
		return code;
	}

	public String getMsgCode() {
		return msgCode;
	}

}