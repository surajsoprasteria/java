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
import java.util.ArrayList;
import java.util.List;

import org.wipo.connect.shared.exchange.dto.impl.Territory;

/**
 * The Class TerritoryHierarchyVO.
 */
public class TerritoryHierarchyVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4080959538715370554L;

    /** The territory. */
    private Territory territory;

    /** The child list. */
    private List<TerritoryHierarchyVO> childList;

    /**
     * Gets the territory.
     *
     * @return the territory
     */
    public Territory getTerritory() {
	return territory;
    }

    /**
     * Sets the territory.
     *
     * @param territory
     *            the new territory
     */
    public void setTerritory(Territory territory) {
	this.territory = territory;
    }

    /**
     * Gets the child list.
     *
     * @return the child list
     */
    public List<TerritoryHierarchyVO> getChildList() {
	if (childList == null) {
	    childList = new ArrayList<>();
	}
	return childList;
    }

    /**
     * Sets the child list.
     *
     * @param childList
     *            the new child list
     */
    public void setChildList(List<TerritoryHierarchyVO> childList) {
	this.childList = childList;
    }

    /**
     * Gets the territory code.
     *
     * @return the territory code
     */
    public String getTerritoryCode() {
	String code = null;

	if (territory != null) {
	    code = territory.getCode();
	}

	return code;
    }

    /**
     * Gets the tisa.
     *
     * @return the tisa
     */
    public String getTisa() {
	String code = null;

	if (territory != null) {
	    code = territory.getTisa();
	}

	return code;
    }

    public String getTerritoryTypeCode() {
	String code = null;

	if (territory != null) {
	    code = territory.getTypeCode();
	}

	return code;
    }
}
