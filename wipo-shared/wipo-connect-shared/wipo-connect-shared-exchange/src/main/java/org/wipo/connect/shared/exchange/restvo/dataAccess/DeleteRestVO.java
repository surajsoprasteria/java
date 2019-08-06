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



package org.wipo.connect.shared.exchange.restvo.dataAccess;



import java.io.Serializable;



/**
 * The Class DeleteRestVO.
 */
public class DeleteRestVO implements Serializable {

	private static final long serialVersionUID = -7570204770826362989L;

	private Long idClientInfo;

	/**
	 * Gets the id client info.
	 *
	 * @return the id client info
	 */
	public Long getIdClientInfo() {
		return idClientInfo;
	}

	/**
	 * Sets the id client info.
	 *
	 * @param idClientInfo the new id client info
	 */
	public void setIdClientInfo(Long idClientInfo) {
		this.idClientInfo = idClientInfo;
	}

}
