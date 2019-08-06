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

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.shared.exchange.dto.impl.InterestedParty;



/**
 * The Class IPDetailVO.
 */
public class InterestedPartyDetailVO implements Serializable {

	  /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4964925414054654559L;

    /** The interested party. */
    private InterestedParty interestedParty;


    
    



    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }



    
    


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
