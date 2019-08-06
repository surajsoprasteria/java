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

import org.apache.commons.lang3.StringUtils;

public enum WccIpImportExceptionCodeEnum {

    NO_ERROR("0"),
    ID_FORMAT_ERROR("1"),
    ID_EMPTY_ERROR("2"),
    IP_MAIN_ID_ERROR("3"),
    TYPE_UNKNOWN_ERROR("4"),
    TYPE_EMPTY_ERROR("5"),
    CITIZENSHIP_UNKNOWN_ERROR("6"),
    SEX_UNKNOWN_ERROR("7"),
    BIRTH_DATE_FORMAT_ERROR("8"),
    DEATH_DATE_FORMAT_ERROR("9"),
    INVALID_DEATH_DATE_ERROR("10"),
    COUNTRY_OF_BIRTH_UNKNOWN_ERROR("11"),
    NAME_MAIN_ID_EMPTY_ERROR("12"),
    NAME_TYPE_UNKNOWN_ERROR("13"),
    NAME_TYPE_EMPTY_ERROR("14"),
    LAST_COMPANY_NAME_EMPTY_ERROR("15"),
    UNKNOWN_CREATION_CLASS("16"),
    UNKNOWN_IPI_ROLE("17"),
    UNKNOWN_IPI_RIGHT_TYPE("18"),
    AFFILIATION_FROM_FORMAT_ERROR("19"),
    AFFILIATION_TO_FORMAT_ERROR("20"),
    INVALID_AFFILIATION_TO("21"),
    SIGNATURE_DATE_FORMAT_ERROR("22"),
    SHARE_FORMAT_ERROR("23"),
    TERRITORY_UNKNOWN_ERROR("24"),
    TERRITORY_CHAIN_ERROR("25"),
    CMO_EMPTY_ERROR("26"),
    CMO_UNKNOWN_ERROR("27"),
    INVALID_AFFILIATION_DOMAIN("28"),
    CONTACT_TYPE_ERROR("29"),
    CONTACT_VALUE_ERROR("30"),
    CODE_EMPTY_ERROR("31"),
    CODE_FORMAT_ERROR("32"),
    VALUE_EMPTY_ERROR("33"),
    DYN_FIELD_ERROR("34"),
    ADDRESS_COUNTRY_ERROR("35"),
    GENERIC_ROW_ERROR("36"),
    TRANSACTION_UNKNOWN("37"),
    TRANSACTION_EMPTY("38"),
    UPDATE_REJECTED("39"),
    DELETE_REJECTED("40"),
    ERROR_DELETING_IP("41"),
    RIGHT_OWNER_ALREADY_PRESENT("42"),
    RIGHT_OWNER_NOT_FOUND("43"),
    ROW_TYPE_EMPTY("44"),
    ROW_MAIN_NOT_UNIQUE("45"),
    PA_NOT_PRESENT("46"),
    TO_MANY_PA_PRESENT("47"),
    AFFILIATION_CONFLICT("48"),
    GENERIC_ERROR("49"),
    WRONG_EXPECTED_ROWS("50"),
    NAME_ID_ALREADY_PRESENT("51"),
    DYN_FIELD_NOT_UNIQUE("52"),
    GROUP_NAME_CONFLICT("53"),
    AFFILIATION_LINE_CONTEXT("54"),
    AFFILIATION_MIXED_SCENARIO("55"),
    IDENTIFIER_VALUE_TYPE_ALREADY_PRESENT("56"),
    DYN_FIELD_CC("57"),
    ROW_TYPE_EMPTY_ERROR("58"),
    FILENAME_ERROR("59"),
    RO_ALREADY_PRESENT("60"),
    AFFILIATION_CONTEXT_CONFLICT("61"),
    IDENTIFIER_CODE_NOT_AVAILABLE("62");

    private final String code;

    private WccIpImportExceptionCodeEnum(String code) {
	this.code = code;
    }

    public String getCode() {
	return code;
    }

    public static WccIpImportExceptionCodeEnum getEnumByCode(String code) {
	for (WccIpImportExceptionCodeEnum ece : WccIpImportExceptionCodeEnum.values()) {
	    if (StringUtils.equals(ece.getCode(), code)) {
		return ece;
	    }
	}
	throw new IllegalArgumentException("Invalid code: " + code);
    }

}
