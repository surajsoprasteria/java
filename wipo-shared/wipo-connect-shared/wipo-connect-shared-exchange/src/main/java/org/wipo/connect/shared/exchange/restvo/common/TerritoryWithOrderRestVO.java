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

package org.wipo.connect.shared.exchange.restvo.common;

import java.io.Serializable;

import org.wipo.connect.shared.exchange.enumerator.TerritoryOrderTypeEnum;

/**
 * The Class TerritoryWithOrderRestVO.
 */
public class TerritoryWithOrderRestVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3257097818846752053L;
	
	/** The terr order type. */
	private TerritoryOrderTypeEnum terrOrderType;

	/**
	 * Gets the terr order type.
	 *
	 * @return the terr order type
	 */
	public TerritoryOrderTypeEnum getTerrOrderType() {
		return terrOrderType;
	}

	/**
	 * Sets the terr order type.
	 *
	 * @param terrOrderType the new terr order type
	 */
	public void setTerrOrderType(TerritoryOrderTypeEnum terrOrderType) {
		this.terrOrderType = terrOrderType;
	}

}
