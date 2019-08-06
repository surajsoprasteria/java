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

import java.util.Date;

import org.wipo.connect.common.dto.Creatable;
import org.wipo.connect.common.dto.Identifiable;
import org.wipo.connect.common.dto.Updatable;

/**
 * The Class Territory Name.
 *
 * @author
 */
public class TerritoryName extends BaseDTO implements Identifiable, Creatable, Updatable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7785313727406869876L;

	private Long idTerritoryName;

	/** The start date. */
	private Date startDate;

	/** The end date. */
	private Date endDate;
	
	private Long fkTerritory;
	
	/**  The territory tis A. */
	private String tisa;

	
	/** The territory fk name. */
	private Long fkName;
	
	/** The id territory fk official name. */
	private Long fkOfficialName;
	
	/** The id territory fk abbreviated name. */
	private Long fkAbbreviatedName;
	
	/** The id territory fk unofficial name. */
	private Long fkUnofficialName;
	

	/** The name. */
	private String name;
	
	/** The officialName. */
	private String officialName;
	
	/** The officialName. */
	private String abbreviatedName;
	
	/** The unofficialName. */
	private String unofficialName;
	
	
	
	
	public Long getIdTerritoryName() {
		return idTerritoryName;
	}

	public void setIdTerritoryName(Long idTerritoryName) {
		this.idTerritoryName = idTerritoryName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getFkTerritory() {
		return fkTerritory;
	}

	public void setFkTerritory(Long fkTerritory) {
		this.fkTerritory = fkTerritory;
	}

	public String getTisa() {
		return tisa;
	}

	public void setTisa(String tisa) {
		this.tisa = tisa;
	}

	public Long getFkName() {
		return fkName;
	}

	public void setFkName(Long fkName) {
		this.fkName = fkName;
	}

	public Long getFkOfficialName() {
		return fkOfficialName;
	}

	public void setFkOfficialName(Long fkOfficialName) {
		this.fkOfficialName = fkOfficialName;
	}

	public Long getFkAbbreviatedName() {
		return fkAbbreviatedName;
	}

	public void setFkAbbreviatedName(Long fkAbbreviatedName) {
		this.fkAbbreviatedName = fkAbbreviatedName;
	}

	public Long getFkUnofficialName() {
		return fkUnofficialName;
	}

	public void setFkUnofficialName(Long fkUnofficialName) {
		this.fkUnofficialName = fkUnofficialName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOfficialName() {
		return officialName;
	}

	public void setOfficialName(String officialName) {
		this.officialName = officialName;
	}

	public String getAbbreviatedName() {
		return abbreviatedName;
	}

	public void setAbbreviatedName(String abbreviatedName) {
		this.abbreviatedName = abbreviatedName;
	}

	public String getUnofficialName() {
		return unofficialName;
	}

	public void setUnofficialName(String unofficialName) {
		this.unofficialName = unofficialName;
	}

	@Override
	public Long getId() {
		return getIdTerritoryName();
	}

	@Override
	public void setId(Long id) {
		setIdTerritoryName(id);
	}


}
