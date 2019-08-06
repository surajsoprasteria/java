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
import java.util.ArrayList;
import java.util.List;


/**
 * The Class FindByIdRestVO.
 */
public class FindByIdRestVO implements Serializable {


    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2881303384280965565L;

    /** The id interested party. */
    private Long idInterestedParty;
    
    /** The code. */
    private String code;
    /** The creationClassCodeList  */
    private List<String> creationClassCodeList;
    
    /**
     * Gets the id interested party.
     *
     * @return the id interested party
     */
    public Long getIdInterestedParty() {
        return idInterestedParty;
    }
    
    /**
     * Sets the id interested party.
     *
     * @param idInterestedParty the new id interested party
     */
    public void setIdInterestedParty(Long idInterestedParty) {
        this.idInterestedParty = idInterestedParty;
    }
	
	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	public List<String> getCreationClassCodeList() {
		if(null==creationClassCodeList){
			creationClassCodeList=new ArrayList<String>();
		}
		return creationClassCodeList;
	}

	public void setCreationClassCodeList(List<String> creationClassCodeList) {
		this.creationClassCodeList = creationClassCodeList;
	}
    
    

}
