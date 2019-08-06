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
 * The Class CheckClientIdentityRestVO.
 */
@SuppressWarnings({"squid:S1948"})
public class CheckClientIdentityRestVO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6273933118248937395L;

	/** The client code. */
	private String clientCode;
	
	/** The client key. */
	private String clientKey;


	/**
	 * Gets the client code.
	 *
	 * @return the client code
	 */
	public String getClientCode() {
		return clientCode;
	}
	
	/**
	 * Sets the client code.
	 *
	 * @param clientCode the new client code
	 */
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	
	/**
	 * Gets the client key.
	 *
	 * @return the client key
	 */
	public String getClientKey() {
		return clientKey;
	}
	
	/**
	 * Sets the client key.
	 *
	 * @param clientKey the new client key
	 */
	public void setClientKey(String clientKey) {
		this.clientKey = clientKey;
	}

}
