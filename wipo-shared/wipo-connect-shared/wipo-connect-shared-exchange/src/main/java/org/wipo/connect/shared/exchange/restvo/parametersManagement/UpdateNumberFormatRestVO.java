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



package org.wipo.connect.shared.exchange.restvo.parametersManagement;



import java.io.Serializable;

import org.wipo.connect.shared.exchange.dto.impl.NumberFormatParam;



public class UpdateNumberFormatRestVO implements Serializable {

	private static final long serialVersionUID = -4726296511379616617L;

	private NumberFormatParam numberFormatParam;

	public NumberFormatParam getNumberFormatParam() {
		return numberFormatParam;
	}

	public void setNumberFormatParam(NumberFormatParam numberFormatParam) {
		this.numberFormatParam = numberFormatParam;
	}

}
