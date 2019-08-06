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



package org.wipo.connect.shared.exchange.restvo.work;



import java.io.Serializable;


/**
 * The Class FindByIdRestVO.
 */
public class FindByMainIdRestVO implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 2881303384280965565L;

    
    private String mainId;


	public String getMainId() {
		return mainId;
	}


	public void setMainId(String mainId) {
		this.mainId = mainId;
	}
   
  

}
