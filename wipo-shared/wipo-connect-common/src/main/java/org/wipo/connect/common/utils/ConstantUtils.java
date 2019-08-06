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
package org.wipo.connect.common.utils;

import java.math.BigDecimal;

/**
 * The Class ConstantUtils.
 */
public class ConstantUtils {

    private ConstantUtils() {
	super();
    }

    /**
     * The Constant WORLD_TERRITORY_CODE.
     */
    public static final String WORLD_TERRITORY_CODE = "2136";

    /**
     * The Constant WORLD_TISA_CODE.
     */
    public static final String WORLD_TISA_CODE = "2WL";

    /**
     * The Constant REFERENCE_CODE_COUNTRY.
     */
    public static final String REFERENCE_CODE_COUNTRY = "CO";

    public static final String REFERENCE_CODE_COPYRIGHT = "OUT_OF_COPYRIGHT";

    public static final String REFERENCE_CODE_WRK_SRC_LOCAL = "WRK_SRC_LOCAL";

    /**
     * The Constant SRC_TYPE_LOCAL_REGISTRATION.
     */
    public static final String SRC_TYPE_LOCAL_REGISTRATION = "Local Registration";

    /**
     * The Constant SRC_TYPE_AGREEMENT.
     */
    public static final String SRC_TYPE_AGREEMENT = "Agreement";

    /**
     * The Constant DYNAMIC_FIELD_PREFIX.
     */
    public static final String DYNAMIC_FIELD_PREFIX = "dyn_";

    /**
     * The Constant MIN_DATE.
     */
    public static final String MIN_DATE = "00010101";

    /**
     * The Constant MAX_DATE.
     */
    public static final String MAX_DATE = "99991231";

    /**
     * The Constant PIPE.
     */
    public static final String PIPE = "|";

    /**
     * The Constant ONE_HUNDRED.
     */
    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    /**
     * The Constant ZERO.
     */
    public static final BigDecimal ZERO = BigDecimal.ZERO;

    /**
     * The Constant M.
     */
    public static final String M = "M";

    /**
     * The Constant F.
     */
    public static final String F = "F";

    /**
     * The Constant TYPE_PATRONIM.
     */
    public static final String TYPE_PATRONIM = "PA";

    /**
     * The Constant TYPE_ORIGINAL_TITLE.
     */
    public static final String TYPE_ORIGINAL_TITLE = "OT";

    /**
     * The Constant WORK_TYPE_CODE_DEFAULT.
     */
    public static final String WORK_TYPE_CODE_DEFAULT = "SIM";

    /**
     * The Constant CHARSET_UTF8.
     */
    public static final String CHARSET_UTF8 = "UTF-8";
    public static final String HTTP_HEADER_USER_NAME = "user-name";
    public static final String HTTP_HEADER_USER_ID = "user-id";

    public static final String HTTP_BASIC_AUTH = "Authorization";

    public static final String SEPARATOR = "-";

    public static final int AUTO_GROW_COLLECTION_LIMIT = 500_000;

    public static final int MAX_ID_LIST_SIZE = 250;

    /**
     * Server Side Pagination default values
     */
    public static final int DEFAULT_LIMIT = 1_000_000;
    public static final int DEFAULT_OFFSET = 0;

    public static final String CSV_NEW_LINE_SEPARATOR = "\n";

    public static final String BOM_CHAR = "\ufeff";

    public static final String KEY_SEP = "\u0000\u0000\u0000";;

}
