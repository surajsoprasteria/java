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
package org.wipo.connect.common.dto;

/**
 * The Interface INumberFormatParam.
 */
public interface INumberFormatParam {

	/**
	 * Gets the decimal separator.
	 *
	 * @return the decimal separator
	 */
	Character getDecimalSeparator();

	/**
	 * Gets the grouping separator.
	 *
	 * @return the grouping separator
	 */
	Character getGroupingSeparator();

	/**
	 * Gets the currency symbol.
	 *
	 * @return the currency symbol
	 */
	String getCurrencySymbol();

	/**
	 * Gets the currency position.
	 *
	 * @return the currency position
	 */
	String getCurrencyPosition();

}