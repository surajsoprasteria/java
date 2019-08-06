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
public enum WorkHeaderFildConstant {

    TRANSACTION(0, false),
    ID(1, false),
    ROW_TYPE(2, false),
    WORK_MAIN_ID(3, false),
    WORK_TITLE(4, false),
    DURATION(5, false),
    GENRE(6, false),
    INSTRUMENT(7, false),
    TYPE(8, false),
    VALUE(9, false),
    PERFORMER(10, false),

    TERRITORY(11, false),
    NAME_MAIN_ID(12, false),
    ROLE(13, false),
    CREATOR_REF(14, false),
    RIGHT_CATEGORY(15, false),

    CREATION_CLASS(16, false),

    CATALOGUE_NUMBER(17, false),
    SUPPORT(18, false),
    COUNTRY_OF_PRODUCTION(19, false),
    CATEGORY(20, false),
    LABEL(21, false),
    LANGUAGE(22, false),

    WEIGHT(23, false),

    TAGS(24, true);

    // TODO Note: IMPORTANT!! Increment index in WorkResultLogHeader

    /** The code. */
    private final Integer code;
    private final Boolean isLocal;

    private WorkHeaderFildConstant(Integer code, Boolean isLocal) {
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
