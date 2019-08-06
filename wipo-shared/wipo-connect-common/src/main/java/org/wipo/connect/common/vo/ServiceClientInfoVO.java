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
package org.wipo.connect.common.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class ServiceClientInfoVO.
 */
public class ServiceClientInfoVO implements Serializable {

	private static final long serialVersionUID = 577145670608570689L;

	private String clientId;
	private String clientKey;
	private List<String> managedCreationClassCodeList;



	/**
	 * Gets the client id.
	 *
	 * @return the client id
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * Sets the client id.
	 *
	 * @param clientId the new client id
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
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

	public List<String> getManagedCreationClassCodeList() {
		if(managedCreationClassCodeList == null){
			managedCreationClassCodeList=new ArrayList<>();
		}
		return managedCreationClassCodeList;
	}

	public void setManagedCreationClassCodeList(List<String> managedCreationClassCodeList) {
		this.managedCreationClassCodeList = managedCreationClassCodeList;
	}

}
