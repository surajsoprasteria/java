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
package org.wipo.connect.common.el;

import java.io.Serializable;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.wipo.connect.common.conversion.AbstractNumericConversionUtils;
import org.wipo.connect.common.spring.SpringUtils;

public class CustomFunctions {

    private CustomFunctions() {
	super();
    }

    public static String init() {
	AbstractNumericConversionUtils ncu = SpringUtils.applicationContext.getBean(AbstractNumericConversionUtils.class);
	ncu.init();
	return StringUtils.EMPTY;
    }

    public static String formatWithScale(Number value, Integer scale) {
	AbstractNumericConversionUtils ncu = SpringUtils.applicationContext.getBean(AbstractNumericConversionUtils.class);
	return ncu.formatWithScale(value, scale);
    }

    public static String formatWithoutSeparator(Number value, Integer scale) {
	AbstractNumericConversionUtils ncu = SpringUtils.applicationContext.getBean(AbstractNumericConversionUtils.class);
	return ncu.formatWithoutSeparator(value, scale);
    }

    public static String format(Number value) {
	return formatWithScale(value, null);
    }

    public static String formatAmountWithScale(Number value, Integer scale) {
	AbstractNumericConversionUtils ncu = SpringUtils.applicationContext.getBean(AbstractNumericConversionUtils.class);
	return ncu.formatAmountWithScale(value, scale);
    }

    public static String formatAmount(Number value) {
	return formatAmountWithScale(value, null);
    }

    public static String getCurrencySymbol() {
	AbstractNumericConversionUtils ncu = SpringUtils.applicationContext.getBean(AbstractNumericConversionUtils.class);
	return ncu.getCurrencySymbol();
    }

    public static String getCurrencyLabel() {
	AbstractNumericConversionUtils ncu = SpringUtils.applicationContext.getBean(AbstractNumericConversionUtils.class);
	return "(" + ncu.getCurrencySymbol() + ")";
    }

    public static Character getDecimalSeparator() {
	AbstractNumericConversionUtils ncu = SpringUtils.applicationContext.getBean(AbstractNumericConversionUtils.class);
	return ncu.getDecimalSeparator();
    }

    public static Character getGroupingSeparator() {
	AbstractNumericConversionUtils ncu = SpringUtils.applicationContext.getBean(AbstractNumericConversionUtils.class);
	return ncu.getGroupingSeparator();
    }

    public static boolean isCurrencyBefore() {
	AbstractNumericConversionUtils ncu = SpringUtils.applicationContext.getBean(AbstractNumericConversionUtils.class);
	return ncu.isCurrencyBefore();
    }

    public static boolean isGroupSeparatorActive() {
	AbstractNumericConversionUtils ncu = SpringUtils.applicationContext.getBean(AbstractNumericConversionUtils.class);
	return ncu.isGroupSeparatorActive();
    }

    public static String getRegexCheck() {
	AbstractNumericConversionUtils ncu = SpringUtils.applicationContext.getBean(AbstractNumericConversionUtils.class);
	return ncu.getRegexCheck();
    }

    public static String getRegexDigitCheck() {
	AbstractNumericConversionUtils ncu = SpringUtils.applicationContext.getBean(AbstractNumericConversionUtils.class);
	return ncu.getRegexDigitCheck();
    }

    public static String getRegexTimeCheck() {
	AbstractNumericConversionUtils ncu = SpringUtils.applicationContext.getBean(AbstractNumericConversionUtils.class);
	return ncu.getRegexTimeCheck();
    }

    public static String[] splitByWholeSeparator(String str, String sep) {
	return StringUtils.splitByWholeSeparator(str, sep);
    }

    public static String joinList(String separator, Collection<? extends Serializable> valueList) {
	if (valueList == null) {
	    return "";
	}
	StringBuilder sb = new StringBuilder();
	valueList.forEach(val -> {
	    sb.append(separator);
	    sb.append(val.toString());
	});
	return StringUtils.removeStart(sb.toString(), separator);
    }

    public static Long hMsToSeconds(String hMs) throws NumberFormatException {
	AbstractNumericConversionUtils ncu = SpringUtils.applicationContext.getBean(AbstractNumericConversionUtils.class);
	return ncu.hMsToSeconds(hMs);
    }

    public static String secondsToHMs(Long second) throws NumberFormatException {
	AbstractNumericConversionUtils ncu = SpringUtils.applicationContext.getBean(AbstractNumericConversionUtils.class);
	return ncu.secondsToHMs(second);
    }

}
