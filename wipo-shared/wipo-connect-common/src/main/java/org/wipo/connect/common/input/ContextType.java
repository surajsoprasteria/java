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

package org.wipo.connect.common.input;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;



/**
 * The Class ContextType.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContextType", propOrder = {
    "requestId",
    "requestTimestemp",
    "clientId",
    "clientKey",
    "managedCreationClassCodeList"
})
public class ContextType {

	/** The request id. */
	protected String requestId;

	/** The request timestemp. */
	@XmlSchemaType(name = "dateTime")
	protected XMLGregorianCalendar requestTimestemp;

	/** The client id. */
	protected String clientId;

	/** The client key. */
	protected String clientKey;

	protected List<String> managedCreationClassCodeList;

	/**
	 * Gets the request id.
	 *
	 * @return the request id
	 */
	public String getRequestId() {
		return requestId;
	}

	/**
	 * Sets the request id.
	 *
	 * @param requestId the new request id
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	/**
	 * Gets the request timestemp.
	 *
	 * @return the request timestemp
	 */
	public XMLGregorianCalendar getRequestTimestemp() {
		return requestTimestemp;
	}

	/**
	 * Sets the request timestemp.
	 *
	 * @param requestTimestemp the new request timestemp
	 */
	public void setRequestTimestemp(XMLGregorianCalendar requestTimestemp) {
		this.requestTimestemp = requestTimestemp;
	}

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
			managedCreationClassCodeList = new ArrayList<>();
		}
		return managedCreationClassCodeList;
	}

}
