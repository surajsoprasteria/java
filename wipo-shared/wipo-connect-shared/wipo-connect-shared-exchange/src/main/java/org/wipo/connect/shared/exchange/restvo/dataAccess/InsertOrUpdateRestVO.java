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

import org.wipo.connect.shared.exchange.dto.impl.ClientInfo;



/**
 * The Class InsertOrUpdateRestVO.
 */
public class InsertOrUpdateRestVO implements Serializable {

	private static final long serialVersionUID = 7712136294574241389L;

	private ClientInfo clientInfo;


	/**
	 * Gets the client info.
	 *
	 * @return the client info
	 */
	public ClientInfo getClientInfo() {
		return clientInfo;
	}

	/**
	 * Sets the client info.
	 *
	 * @param clientInfo the new client info
	 */
	public void setClientInfo(ClientInfo clientInfo) {
		this.clientInfo = clientInfo;
	}
}
