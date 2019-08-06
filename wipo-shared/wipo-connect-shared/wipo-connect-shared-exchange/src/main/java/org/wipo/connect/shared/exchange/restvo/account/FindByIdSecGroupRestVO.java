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



package org.wipo.connect.shared.exchange.restvo.account;



import java.io.Serializable;



/**
 * The Class FindByIdSecGroupRestVO.
 */
public class FindByIdSecGroupRestVO implements Serializable {

    private static final long serialVersionUID = 2881303384280965565L;
   
    private Long idSecGroup;

	/**
	 * Gets the id sec group.
	 *
	 * @return the id sec group
	 */
	public Long getIdSecGroup() {
		return idSecGroup;
	}

	/**
	 * Sets the id sec group.
	 *
	 * @param idSecGroup the new id sec group
	 */
	public void setIdSecGroup(Long idSecGroup) {
		this.idSecGroup = idSecGroup;
	}
}
