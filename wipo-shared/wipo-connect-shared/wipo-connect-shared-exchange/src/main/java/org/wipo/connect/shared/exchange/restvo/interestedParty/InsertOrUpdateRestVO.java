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



package org.wipo.connect.shared.exchange.restvo.interestedParty;



import java.io.Serializable;

import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;



/**
 * The Class InsertOrUpdateRestVO.
 */
public class InsertOrUpdateRestVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8608457897315042066L;

	/** The interested party. */
	private InterestedParty interestedParty;

	/**
	 * Gets the interested party.
	 *
	 * @return the interested party
	 */
	public InterestedParty getInterestedParty() {
		return interestedParty;
	}

	/**
	 * Sets the interested party.
	 *
	 * @param interestedParty the new interested party
	 */
	public void setInterestedParty(InterestedParty interestedParty) {
		this.interestedParty = interestedParty;
	}

}
