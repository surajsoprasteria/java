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

/**
 * The Class ConversionUtils.
 */
public class ConversionUtils {

    /** The Constant DATE_PATTERN. */
    public static final String DATE_PATTERN = "dd/MM/yyyy";

    /**
     * The Constant DATE_PATTERN_REPORT.
     */
    public static final String DATE_PATTERN_REPORT = "yyyy/MM/dd";

    /** The Constant DATE_PATTERN. */
    public static final String DATE_PATTERN_REVERSE = "yyyy/MM/dd";

    /** The Constant DATE_PATTERN_ISO8601. */
    public static final String DATE_PATTERN_ISO8601 = "yyyy-MM-dd HH:mm:ss";

    /** The Constant DATE_STAMP. */
    public static final String DATE_STAMP = "yyyyMMdd";

    /**
     * The Constant DATE_STAMP_SEP.
     */
    public static final String DATE_STAMP_SEP = "yyyy-MM-dd";

    /** The Constant TIME_PATTERN. */
    public static final String TIME_PATTERN = "HH:mm";

    /** The Constant TIME_STAMP. */
    public static final String TIME_STAMP = "HHmmss";

    /** The Constant DATE_TIME_PATTERN. */
    public static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm";

    /** The Constant DATE_TIME_STAMP. */
    public static final String DATE_TIME_STAMP = "yyyyMMddHHmmss";

    /** The Constant DATE_TIME_STAMP_V2. */
    public static final String DATE_TIME_STAMP_V2 = "yyyyMMdd HHmmss";

    public static final String DATE_TIME_STAMP_MILLI = "yyyyMMddHHmmssSSS";

    public static final String DATE_TIME_STAMP_MILLI_V2 = "yyyyMMdd HHmmss SSS";

    public static final String DECIMAL_PATTERN = "###.00###";

    public static final String DECIMAL_PATTERN_OPTIONAL = "###.#####";

    public static final String DECIMAL_PATTERN_SEP = "#,##0.00###";

    public static final String DECIMAL_PATTERN_SEP_OPTIONAL = "#,###.###";

    /**
     * Instantiates a new conversion utils.
     */
    private ConversionUtils() {
	super();
    }
}
