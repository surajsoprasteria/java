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

package org.wipo.connect.shared.exchange.vo;

import java.io.Serializable;

import org.wipo.connect.shared.exchange.dto.impl.Cmo;

/**
 * The Class CmoSearchVO.
 */
public class CmoVO implements Serializable {

	private static final long serialVersionUID = -894160133424347366L;
	
	private Cmo cmo;

	public Cmo getCmo() {
		return cmo;
	}

	public void setCmo(Cmo cmo) {
		this.cmo = cmo;
	}


}
