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



/**
 * The Class FindIpByNameRestVO.
 */
public class FindIpByNameRestVO implements Serializable {

	private static final long serialVersionUID = 5849101165174674351L;
	private Long idName;
	
	/**
	 * Gets the id name.
	 *
	 * @return the id name
	 */
	public Long getIdName() {
		return idName;
	}
	
	/**
	 * Sets the id name.
	 *
	 * @param idName the new id name
	 */
	public void setIdName(Long idName) {
		this.idName = idName;
	}

}
