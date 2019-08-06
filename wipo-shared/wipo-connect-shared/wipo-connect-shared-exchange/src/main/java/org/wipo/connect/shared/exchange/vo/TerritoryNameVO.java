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

import org.wipo.connect.shared.exchange.dto.impl.TerritoryName;

/**
 * The Class TerritoryNameVO.
 */
public class TerritoryNameVO implements Serializable {

	private static final long serialVersionUID = -894160133424347366L;
	
	private TerritoryName territoryName;

	public TerritoryName getTerritoryName() {
		return territoryName;
	}

	public void setTerritoryName(TerritoryName territoryName) {
		this.territoryName = territoryName;
	}


}
