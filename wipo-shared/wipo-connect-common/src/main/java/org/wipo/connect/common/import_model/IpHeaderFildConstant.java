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
package org.wipo.connect.common.import_model;

/**
 * The Class HeaderFildConstant.
 */
public enum IpHeaderFildConstant {

    // Technical field
    TRANSACTION(0, false),
    ID(1, false),
    ROW_TYPE(2, false),

    // Main field
    MAIN_ID(3, false),
    TYPE(4, false),
    CITIZENSHIP(5, false),
    SEX(6, false),
    BIRTH_DATE(7, false),
    DEATH_DATE(8, false),
    BIRTH_PLACE(9, false), // TODO: WCONNECT-63 ------------------------------
    COUNTRY_OF_BIRTH(10, false),

    // Name field
    NAME_TYPE(11, false),
    LAST_COMPANY_NAME(12, false),
    FIRST_NAME(13, false),

    // Affiliation field
    CREATION_CLASS(14, false),
    RIGHT_TYPE(15, false),
    ROLE(16, false),
    AFFILIATION_FROM(17, false),
    AFFILIATION_TO(18, false),
    SIGNATURE_DATE(19, false),
    SHARE(20, false),
    TERRITORY(21, false),
    CMO(22, false),

    // IDENTIFIER
    VALUE(23, false),

    // ADDRESS FIELD
    ADDRESS_LINE_1(24, true),
    ADDRESS_LINE_2(25, true),
    ADDRESS_LINE_3(26, true),
    ADDRESS_CITY(27, true),
    ADDRESS_PROVINCE(28, true),
    ADDRESS_ZIP_CODE(29, true),
    ADDRESS_COUNTRY(30, true),

    // BANK ACCOUNT FIELD
    BANK_NAME(31, true),
    BRANCH(32, true),
    ADDRESS(33, true),
    ACCOUNT_NAME(34, true),
    SWIFT(35, true),
    ACCOUNT_NUMBER(36, true),
    TYPE_OF_PAYMENT(37, true),

    // TAGS
    TAGS(38, true);

    /** The code. */
    private final Integer code;
    private final Boolean isLocal;

    private IpHeaderFildConstant(Integer code, Boolean isLocal) {
	this.code = code;
	this.isLocal = isLocal;
    }

    public Integer getCode() {
	return code;
    }

    public Boolean isLocal() {
	return isLocal;
    }

}
