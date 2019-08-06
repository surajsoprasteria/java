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

public enum WccWorkImportExceptionCodeEnum {

    NO_ERROR("0"),
    ID_FORMAT_ERROR("1"),
    ID_EMPTY_ERROR("2"),
    MAIN_ID_EMPTY("3"),
    WORK_TITLE_EMPTY("4"),
    WORK_TITLE_TYPE_UNKNOWN("5"),
    DURATION_FORMAT_ERROR("6"),
    GENRE_ERROR("7"),
    WORK_CREATION_DATE_FORMAT_ERROR("8"),
    IDENTIFIER_CODE_UNKNOWN("9"),
    IDENTIFIER_CODE_EMPTY("10"),
    IDENTIFIER_VALUE_TYPE_ALREADY_PRESENT("11"),
    IDENTIFIER_VALUE_EMPTY("12"),
    TERRITORY_FORMULA_ERROR("13"),
    NAME_MAIN_ID_NOT_FOUND("14"),
    ROLE_UNKNOWN("15"),
    CREATOR_REF_ERROR("16"),
    RIGHT_CATEGORY_ERROR("17"),
    RIGHT_CATEGORY_EMPTY("18"),
    RIGHT_CATEGORY_VALIDATION_ERROR("19"),
    INSTRUMENT_UNKNOWN("20"),
    UNKNOWN_ERROR("21"),
    TRANSACTION_UNKNOWN("22"),
    TRANSACTION_EMPTY("23"),
    MAINID_ALREADY_USE_ERROR("24"),
    WORK_NOT_EXIST_ERROR("25"),
    ROW_TYPE_EMPTY("26"),
    ROW_MAIN_NOT_UNIQUE("27"),
    WORK_TITLE_OT_MANDATORY("28"),
    TOO_MANY_TITLE_OT("29"),
    SHARE_ERROR("30"),
    UNKNOWN_ERROR_ENTITY("31"),
    GENERIC_ERROR("32"),
    MAIN_ROW_MANDATORY("33"),
    FILENAME_ERROR("34"),
    DELETE_TRANSACTION_ERROR("35"),
    DYN_FIELD_CODE_ERROR("36"),
    DYN_FIELD_VALUE_ERROR("37"),
    DELETE_WORK_DENIED("38"),
    GROUP_NOT_EMPTY("39"),
    GROUP_CREATION_CLASS("40"),
    COMPONENT_NOT_EMPTY("41"),
    COMPONENT_NOT_FOUND("42"),
    COMPONENT_WEIGHT("43"),
    COMPONENT_NUMBER("44"),
    COMPONENT_CYCLING("45"),
    WORK_PERCENTAGE("46"),
    COUNTRY_CODE_ERROR("47"),
    CATEGORY_CODE_ERROR("48"),
    DATE_TYPE_ERROR("49"),
    DATE_TYPE_DUPLICATE("50"),
    DATE_NOT_VALID("51"),
    AF_CREATION_CLASS("52"),
    DATE_CREATION_CLASS("53"),
    CREATION_CLASS_EMPTY("54"),
    CREATION_CLASS_NOT_VALID("55"),
    CREATION_CLASS_DIFFERENT("56"),
    CREATION_CLASS_NOT_MNGT("57"),
    COMPONENT_DUPLICATE("58"),
    COMPONENT_WEIGHT_MUST_BE_NULL("59"),
    WORK_IS_COMPONENT("60"),
    ROLE_NOT_VALID_FOR_CREATION_CLASS("61"),
    DYN_FIELD_NOT_UNIQUE("62"),
    TERRITORY_MANDATORY("63"),
    IDENTIFIER_CODE_NOT_AVAILABLE("64");

    private final String code;

    private WccWorkImportExceptionCodeEnum(String code) {
	this.code = code;
    }

    public String getCode() {
	return code;
    }

    public static WccWorkImportExceptionCodeEnum getEnumByCode(String code) {
	for (WccWorkImportExceptionCodeEnum ece : WccWorkImportExceptionCodeEnum.values()) {
	    if (StringUtils.equals(ece.getCode(), code)) {
		return ece;
	    }
	}
	throw new IllegalArgumentException("Invalid code: " + code);
    }

}
