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
import java.util.List;

import org.wipo.connect.shared.exchange.dto.impl.Affiliation;



public class ExplodeIpiRightTypeRestVO implements Serializable {

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5936408381747549650L;


	private List<Affiliation> affiliationList;
    

	private boolean removeAd;


	public List<Affiliation> getAffiliationList() {
		return affiliationList;
	}


	public void setAffiliationList(List<Affiliation> affiliationList) {
		this.affiliationList = affiliationList;
	}


	public boolean isRemoveAd() {
		return removeAd;
	}


	public void setRemoveAd(boolean removeAd) {
		this.removeAd = removeAd;
	}



}