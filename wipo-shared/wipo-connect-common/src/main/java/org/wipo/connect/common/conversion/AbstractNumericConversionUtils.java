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
package org.wipo.connect.common.conversion;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.wipo.connect.common.dto.INumberFormatParam;
import org.wipo.connect.common.utils.ConversionUtils;
import org.wipo.connect.enumerator.CurrencyPositionEnum;

public abstract class AbstractNumericConversionUtils {
    private static final int MAX_DECIMAL_POSITION = 5;
    private static final int MIN_DECIMAL_POSITION = 2;

    private boolean isInitialized;
    // private DecimalFormat decimalFormat;
    // private DecimalFormat integerFormat;
    private boolean currencyBefore;
    private String currencySymbol;
    private Character decimalSeparator;
    private Character groupingSeparator;
    private boolean groupSeparatorActive;
    private String regexCheck;
    private String regexDigitCheck;
    private String regexTimeCheck;

    public abstract void init();

    public void init(INumberFormatParam numericFormatParams) {
	if (numericFormatParams == null) {
	    decimalSeparator = '.';
	    groupingSeparator = null;
	    groupSeparatorActive = groupingSeparator != null;
	    currencyBefore = true;
	    currencySymbol = "EUR";
	} else {
	    decimalSeparator = numericFormatParams.getDecimalSeparator();
	    groupingSeparator = numericFormatParams.getGroupingSeparator();
	    groupSeparatorActive = groupingSeparator != null;
	    currencyBefore = StringUtils.equalsIgnoreCase(numericFormatParams.getCurrencyPosition(), CurrencyPositionEnum.BEFORE.name());
	    currencySymbol = numericFormatParams.getCurrencySymbol();
	}

	regexCheck = "^(\\-)?\\d{1,3}(" + regexEscape(groupingSeparator) + "\\d{3})*(" + regexEscape(decimalSeparator) + "\\d+)?$";
	regexDigitCheck = "^(\\-)?\\d{1,3}(" + regexEscape(groupingSeparator) + "\\d{3})*?$";
	regexTimeCheck = "(^[0-5]?[0-9]$)|(^[0-5]?[0-9]{1}:([0-5][0-9]){1}$)|(^[0-9]+:([0-5][0-9]){1}:([0-5][0-9]){1}$)";

	isInitialized = numericFormatParams != null;
    }

    private DecimalFormat initDecimalFormat() {
	DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
	decimalFormatSymbols.setDecimalSeparator(decimalSeparator);
	if (groupSeparatorActive) {
	    decimalFormatSymbols.setGroupingSeparator(groupingSeparator);
	}

	DecimalFormat decimalFormat = new DecimalFormat();
	decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
	decimalFormat.applyPattern(ConversionUtils.DECIMAL_PATTERN_SEP);
	decimalFormat.setParseBigDecimal(true);
	decimalFormat.setGroupingUsed(groupSeparatorActive);

	return decimalFormat;
    }

    private DecimalFormat initIntegerFormat() {
	DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
	decimalFormatSymbols.setDecimalSeparator(decimalSeparator);
	if (groupSeparatorActive) {
	    decimalFormatSymbols.setGroupingSeparator(groupingSeparator);
	}

	DecimalFormat integerFormat = new DecimalFormat();
	integerFormat.setDecimalFormatSymbols(decimalFormatSymbols);
	integerFormat.applyPattern(ConversionUtils.DECIMAL_PATTERN_SEP_OPTIONAL);
	integerFormat.setParseBigDecimal(false);
	integerFormat.setGroupingUsed(groupSeparatorActive);

	return integerFormat;
    }

    public String format(Number value) {
	return formatWithScale(value, null);
    }

    public String formatWithScale(Number value, Integer scale) {
	if (!isInitialized)
	    init();

	int minDecimalPosition = scale == null ? MIN_DECIMAL_POSITION : scale;
	int maxDecimalPosition = scale == null ? MAX_DECIMAL_POSITION : scale;

	if (value == null)
	    return null;

	if (value instanceof Byte || value instanceof Integer || value instanceof Long || value instanceof Short || value instanceof BigInteger) {
	    return initIntegerFormat().format(value);
	} else if (value instanceof BigDecimal) {
	    DecimalFormat auxDF = initDecimalFormat();
	    auxDF.setMinimumFractionDigits(minDecimalPosition);
	    auxDF.setMaximumFractionDigits(maxDecimalPosition);
	    return auxDF.format(value);
	} else if (value instanceof Double) {
	    DecimalFormat auxDF = initDecimalFormat();
	    auxDF.setMinimumFractionDigits(1);
	    auxDF.setMaximumFractionDigits(20);
	    return auxDF.format(value);
	} else {
	    throw new IllegalArgumentException(value.getClass().getName() + " type is not supported");
	}

    }

    public String formatWithoutSeparator(Number value, Integer scale) {
	if (!isInitialized)
	    init();

	int minDecimalPosition = scale == null ? MIN_DECIMAL_POSITION : scale;
	int maxDecimalPosition = scale == null ? MAX_DECIMAL_POSITION : scale;
	DecimalFormatSymbols customDecimalFormatSymbols = new DecimalFormatSymbols();

	if (value == null)
	    return null;

	if (value instanceof Byte || value instanceof Integer || value instanceof Long || value instanceof Short || value instanceof BigInteger) {
	    DecimalFormat auxIF = initIntegerFormat();
	    auxIF.setDecimalFormatSymbols(customDecimalFormatSymbols);
	    return initIntegerFormat().format(value);
	} else if (value instanceof BigDecimal) {
	    DecimalFormat auxDF = initDecimalFormat();
	    auxDF.setMinimumFractionDigits(minDecimalPosition);
	    auxDF.setMaximumFractionDigits(maxDecimalPosition);
	    auxDF.setDecimalFormatSymbols(customDecimalFormatSymbols);
	    return auxDF.format(value);
	} else if (value instanceof Double || value instanceof Float) {
	    throw new IllegalArgumentException(value.getClass().getName() + " type is not supported,  please use java.math.BigDecimal instead");
	} else {
	    throw new IllegalArgumentException(value.getClass().getName() + " type is not supported");
	}

    }

    public String formatAmount(Number value) {
	return formatAmountWithScale(value, null);
    }

    public String formatAmountWithScale(Number value, Integer scale) {
	if (!isInitialized)
	    init();

	if (value == null)
	    return null;

	String formattedAmount = formatWithScale(value, scale);
	StringBuilder sb = new StringBuilder();
	if (currencyBefore) {
	    sb.append(currencySymbol).append(" ").append(formattedAmount);
	} else {
	    sb.append(formattedAmount).append(" ").append(currencySymbol);
	}

	return sb.toString();
    }

    public BigDecimal parseDecimal(String value) {
	return parseDecimal(value, null);
    }

    public BigDecimal parseDecimal(String value, Integer scale) {
	if (!isInitialized)
	    init();

	int minDecimalPosition = scale == null ? MIN_DECIMAL_POSITION : scale;
	int maxDecimalPosition = scale == null ? MAX_DECIMAL_POSITION : scale;

	String strValue = StringUtils.trimToNull(value);
	if (strValue == null || StringUtils.equals(strValue, "null")) {
	    return null;
	}

	if (!Pattern.matches(regexCheck, strValue)) {
	    throw new IllegalArgumentException("Error parsing the value " + strValue + ", invalid format");
	}

	BigDecimal aux;
	try {
	    aux = (BigDecimal) initDecimalFormat().parse(strValue);

	    if (aux.scale() < minDecimalPosition) {
		aux = aux.setScale(minDecimalPosition, RoundingMode.HALF_UP);
	    } else if (aux.scale() > maxDecimalPosition) {
		aux = aux.setScale(maxDecimalPosition, RoundingMode.HALF_UP);
	    }
	} catch (Exception e) {
	    throw new IllegalArgumentException("Error parsing the value " + strValue, e);
	}

	return aux;
    }

    public Long parseLong(String value) {
	if (!isInitialized)
	    init();

	try {
	    String strValue = StringUtils.trimToNull(value);
	    if (strValue == null || StringUtils.equals(strValue, "null")) {
		return null;
	    }

	    Number out = initIntegerFormat().parse(strValue);
	    return out.longValue();

	} catch (Exception e) {
	    throw new IllegalArgumentException("Error parsing the value " + value, e);
	}
    }

    public Double parseDouble(String value) {
	if (!isInitialized)
	    init();

	try {
	    String strValue = StringUtils.trimToNull(value);
	    if (strValue == null || StringUtils.equals(strValue, "null")) {
		return null;
	    }

	    Number out = initDecimalFormat().parse(strValue);
	    return out.doubleValue();

	} catch (Exception e) {
	    throw new IllegalArgumentException("Error parsing the value " + value, e);
	}
    }

    public Integer parseInteger(String value) {
	if (!isInitialized)
	    init();

	try {
	    String strValue = StringUtils.trimToNull(value);
	    if (strValue == null || StringUtils.equals(strValue, "null")) {
		return null;
	    }
	    Number out = initIntegerFormat().parse(strValue);
	    return out.intValue();
	} catch (Exception e) {
	    throw new IllegalArgumentException("Error parsing the value " + value, e);
	}
    }

    public BigInteger parseBigInteger(String value) {
	if (!isInitialized)
	    init();

	try {
	    String strValue = StringUtils.trimToNull(value);
	    if (strValue == null || StringUtils.equals(strValue, "null")) {
		return null;
	    }
	    Number aux = initIntegerFormat().parse(strValue);
	    BigInteger out = new BigInteger(aux.toString());
	    return out;
	} catch (Exception e) {
	    throw new IllegalArgumentException("Error parsing the value " + value, e);
	}
    }

    public BigDecimal parseAmount(String value) {
	return parseAmount(value);
    }

    public BigDecimal parseAmount(String value, Integer scale) {
	if (!isInitialized)
	    init();

	if (value == null)
	    return null;

	return parseDecimal(StringUtils.replace(value, currencySymbol, ""), scale);
    }

    private String regexEscape(Character c) {
	if (c == null)
	    return "";

	switch (c) {
	    case '\\':
	    case '.':
	    case '[':
	    case ']':
	    case '{':
	    case '}':
	    case '(':
	    case ')':
	    case '*':
	    case '+':
	    case '-':
	    case '?':
	    case '^':
	    case '$':
	    case '|':
		return "\\" + c;

	    case ' ':
		return "\\s";

	    default:
		return "" + c;
	}
    }

    public boolean isInitialized() {
	return isInitialized;
    }

    public boolean isCurrencyBefore() {
	if (!isInitialized)
	    init();
	return currencyBefore;
    }

    public String getCurrencySymbol() {
	if (!isInitialized)
	    init();
	return currencySymbol;
    }

    public Character getDecimalSeparator() {
	if (!isInitialized)
	    init();
	return decimalSeparator;
    }

    public Character getGroupingSeparator() {
	if (!isInitialized)
	    init();
	return groupingSeparator;
    }

    public boolean isGroupSeparatorActive() {
	if (!isInitialized)
	    init();
	return groupSeparatorActive;
    }

    public String getRegexCheck() {
	if (!isInitialized)
	    init();
	return regexCheck;
    }

    public String getRegexDigitCheck() {
	if (!isInitialized)
	    init();
	return regexDigitCheck;
    }

    public String getRegexTimeCheck() {
	if (!isInitialized)
	    init();
	return regexTimeCheck;
    }

    public Long hMsToSeconds(String hMs) throws NumberFormatException {
	String[] a = hMs.split(":"); // split it at the colons
	if (a != null && a.length == 3) {
	    return Long.parseLong(a[0]) * 60 * 60 + (Long.parseLong(a[1])) * 60 + (Long.parseLong(a[2]));
	} else if (a != null && a.length == 2) {
	    return Long.parseLong(a[0]) * 60 + Long.parseLong(a[1]);
	} else if (a != null && a.length == 1) {
	    return Long.parseLong(a[0]);
	}
	throw new NumberFormatException("String hMs is not format correctly");
    }

    public String secondsToHMs(Long s) throws NumberFormatException {

	if (s == null) {
	    return null;
	}

	Long hours = s / 3600;
	Long minutes = (s % 3600) / 60;
	Long seconds = s % 60;

	if (hours > 0) {
	    return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	} else if (hours == 0 && minutes > 0) {
	    return String.format("%02d:%02d", minutes, seconds);
	} else {
	    return String.format("%02d", seconds);
	}
    }

}
