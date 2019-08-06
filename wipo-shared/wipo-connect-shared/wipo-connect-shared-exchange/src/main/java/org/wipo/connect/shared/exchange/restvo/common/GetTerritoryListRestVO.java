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

@SuppressWarnings({ "squid:S1948" })
public class GetTerritoryListRestVO implements Serializable {

    private static final long serialVersionUID = 5787998960065476796L;

    private TerritoryOrderTypeEnum territoryOrderType;

    public TerritoryOrderTypeEnum getTerritoryOrderType() {
	return territoryOrderType;
    }

    public void setTerritoryOrderType(TerritoryOrderTypeEnum territoryOrderType) {
	this.territoryOrderType = territoryOrderType;
    }

}
