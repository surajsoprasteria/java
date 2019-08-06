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

package org.wipo.connect.shared.exchange.dto.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.wipo.connect.common.crypto.CryptedField;
import org.wipo.connect.common.dto.Identifiable;

/**
 * The Class ClientInfo.
 */
public class ClientInfo implements Identifiable, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5839368387952875421L;

	/** The id client info. */
	private Long idClientInfo;

	/** The client key. */
	@CryptedField
	private String clientKey;

	private Long fkCmo;

	private Cmo cmo;

	private List<Cmo> dataOriginList;

	private String notes;




	@Override
	public Long getId() {
		return getIdClientInfo();
	}


	@Override
	public void setId(Long id) {
		setIdClientInfo(id);
	}

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

	/**
	 * Gets the fk cmo.
	 *
	 * @return the fk cmo
	 */
	public Long getFkCmo() {
		return fkCmo;
	}

	/**
	 * Sets the fk cmo.
	 *
	 * @param fkCmo the new fk cmo
	 */
	public void setFkCmo(Long fkCmo) {
		this.fkCmo = fkCmo;
	}

	/**
	 * Gets the cmo.
	 *
	 * @return the cmo
	 */
	public Cmo getCmo() {
		return cmo;
	}

	/**
	 * Sets the cmo.
	 *
	 * @param cmo the new cmo
	 */
	public void setCmo(Cmo cmo) {
		this.cmo = cmo;
	}

	/**
	 * Gets the data origin list.
	 *
	 * @return the data origin list
	 */
	public List<Cmo> getDataOriginList() {
		if(dataOriginList== null){
			dataOriginList = new ArrayList<>();
		}
		return dataOriginList;
	}

	/**
	 * Sets the data origin list.
	 *
	 * @param dataOriginList the new data origin list
	 */
	public void setDataOriginList(List<Cmo> dataOriginList) {
		this.dataOriginList = dataOriginList;
	}

	/**
	 * Gets the data origin code list.
	 *
	 * @return the data origin code list
	 */
	public List<String> getDataOriginCodeList() {
		List<String> auxList = new ArrayList<>();

		if(!getDataOriginList().isEmpty()){
			auxList = getDataOriginList()
					.stream()
					.filter(cmo -> StringUtils.isNotBlank(cmo.getCode()))
					.map(cmo -> cmo.getCode())
					.collect(Collectors.toList());
			Collections.sort(auxList);
		}

		return auxList;
	}

	/**
	 * Gets the notes.
	 *
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * Sets the notes.
	 *
	 * @param notes the new notes
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

}
