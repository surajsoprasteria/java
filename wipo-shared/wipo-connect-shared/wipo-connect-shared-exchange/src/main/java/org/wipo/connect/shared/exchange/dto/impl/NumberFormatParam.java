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

package org.wipo.connect.shared.exchange.dto.impl;

import org.wipo.connect.common.dto.INumberFormatParam;
import org.wipo.connect.common.dto.Identifiable;

public class NumberFormatParam extends BaseDTO implements Identifiable, INumberFormatParam {

	private static final long serialVersionUID = -5005804353096774043L;

	private Long idNumberFormatParam;
	private Character decimalSeparator;
	private Character groupingSeparator;
	private String currencySymbol;
	private String currencyPosition;

	@Override
	public Long getId() {
		return getIdNumberFormatParam();
	}

	@Override
	public void setId(Long id) {
		setIdNumberFormatParam(id);
	}

	public Long getIdNumberFormatParam() {
		return idNumberFormatParam;
	}

	public void setIdNumberFormatParam(Long idNumberFormatParam) {
		this.idNumberFormatParam = idNumberFormatParam;
	}

	@Override
	public Character getDecimalSeparator() {
		return decimalSeparator;
	}

	public void setDecimalSeparator(Character decimalSeparator) {
		this.decimalSeparator = decimalSeparator;
	}

	@Override
	public Character getGroupingSeparator() {
		return groupingSeparator;
	}

	public void setGroupingSeparator(Character groupingSeparator) {
		this.groupingSeparator = groupingSeparator;
	}

	@Override
	public String getCurrencySymbol() {
		return currencySymbol;
	}

	public void setCurrencySymbol(String currencySymbol) {
		this.currencySymbol = currencySymbol;
	}

	@Override
	public String getCurrencyPosition() {
		return currencyPosition;
	}

	public void setCurrencyPosition(String currencyPosition) {
		this.currencyPosition = currencyPosition;
	}

}
