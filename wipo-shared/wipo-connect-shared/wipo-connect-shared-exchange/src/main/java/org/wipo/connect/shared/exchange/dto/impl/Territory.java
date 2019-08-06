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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Creatable;
import org.wipo.connect.common.dto.Identifiable;
import org.wipo.connect.common.dto.Updatable;

/**
 * The Class Territory.
 *
 * @author minervini
 */
public class Territory extends BaseDTO implements Identifiable, Creatable, Updatable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 7785313727406869876L;

    private Long idTerritory;

    /** The start date. */
    private Date startDate;

    /** The end date. */
    private Date endDate;

    /** The id territory name. */
    private Long idTerritoryName;

    /** The id territory fk name. */
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

    /** The fk type. */
    private Long fkType;

    /** The code. */
    private String code;

    /** The territory identifier value. */
    private String identifierValue;

    /** The territory tis A. */
    private String tisa;

    /** The is managed by cmo. */
    private Boolean isManagedByCmo;

    private List<TerritoryName> territoryNameList;

    private String typeCode;

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
	return this.code;
    }

    /**
     * Gets the end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
	return this.endDate;
    }

    /**
     * Gets the fk type.
     *
     * @return the fk type
     */
    public Long getFkType() {
	return this.fkType;
    }

    @Override
    public Long getId() {
	return getIdTerritory();
    }

    /**
     * Gets the id territory.
     *
     * @return the id territory
     */
    public Long getIdTerritory() {
	return this.idTerritory;
    }

    /**
     * Gets the id territory name.
     *
     * @return the id territory name
     */
    public Long getIdTerritoryName() {
	return this.idTerritoryName;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
	return this.name;
    }

    /**
     * Gets the start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
	return this.startDate;
    }

    /**
     * Sets the code.
     *
     * @param code
     *            the new code
     */
    public void setCode(String code) {
	this.code = code;
    }

    /**
     * Sets the end date.
     *
     * @param endDate
     *            the new end date
     */
    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }

    /**
     * Sets the fk type.
     *
     * @param fkType
     *            the new fk type
     */
    public void setFkType(Long fkType) {
	this.fkType = fkType;
    }

    @Override
    public void setId(Long id) {
	setIdTerritory(id);
    }

    /**
     * Sets the id territory.
     *
     * @param idTerritory
     *            the new id territory
     */
    public void setIdTerritory(Long idTerritory) {
	this.idTerritory = idTerritory;
    }

    /**
     * Sets the id territory name.
     *
     * @param idTerritoryName
     *            the new id territory name
     */
    public void setIdTerritoryName(Long idTerritoryName) {
	this.idTerritoryName = idTerritoryName;
    }

    /**
     * Sets the name.
     *
     * @param name
     *            the new name
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * Sets the start date.
     *
     * @param startDate
     *            the new start date
     */
    public void setStartDate(Date startDate) {
	this.startDate = startDate;
    }

    /**
     * Gets the identifierValue.
     *
     * @return the identifierValue
     */
    public String getIdentifierValue() {
	return identifierValue;
    }

    /**
     * Sets the identifierValue.
     *
     * @param identifierValue
     *            the new identifier value
     */
    public void setIdentifierValue(String identifierValue) {
	this.identifierValue = identifierValue;
    }

    @Override
    public String toString() {
	return ReflectionToStringBuilder.toString(this);
    }

    /**
     * Gets the official name.
     *
     * @return the official name
     */
    public String getOfficialName() {
	return officialName;
    }

    /**
     * Sets the official name.
     *
     * @param officialName
     *            the new official name
     */
    public void setOfficialName(String officialName) {
	this.officialName = officialName;
    }

    /**
     * Gets the abbreviated name.
     *
     * @return the abbreviated name
     */
    public String getAbbreviatedName() {
	return abbreviatedName;
    }

    /**
     * Sets the abbreviated name.
     *
     * @param abbreviatedName
     *            the new abbreviated name
     */
    public void setAbbreviatedName(String abbreviatedName) {
	this.abbreviatedName = abbreviatedName;
    }

    /**
     * Gets the unofficial name.
     *
     * @return the unofficial name
     */
    public String getUnofficialName() {
	return unofficialName;
    }

    /**
     * Sets the unofficial name.
     *
     * @param unofficialName
     *            the new unofficial name
     */
    public void setUnofficialName(String unofficialName) {
	this.unofficialName = unofficialName;
    }

    /**
     * Gets the tisa.
     *
     * @return the tisa
     */
    public String getTisa() {
	return tisa;
    }

    /**
     * Sets the tisa.
     *
     * @param tisa
     *            the new tisa
     */
    public void setTisa(String tisa) {
	this.tisa = tisa;
    }

    /**
     * Gets the fk name.
     *
     * @return the fk name
     */
    public Long getFkName() {
	return fkName;
    }

    /**
     * Sets the fk name.
     *
     * @param fkName
     *            the new fk name
     */
    public void setFkName(Long fkName) {
	this.fkName = fkName;
    }

    /**
     * Gets the fk official name.
     *
     * @return the fk official name
     */
    public Long getFkOfficialName() {
	return fkOfficialName;
    }

    /**
     * Sets the fk official name.
     *
     * @param fkOfficialName
     *            the new fk official name
     */
    public void setFkOfficialName(Long fkOfficialName) {
	this.fkOfficialName = fkOfficialName;
    }

    /**
     * Gets the fk abbreviated name.
     *
     * @return the fk abbreviated name
     */
    public Long getFkAbbreviatedName() {
	return fkAbbreviatedName;
    }

    /**
     * Sets the fk abbreviated name.
     *
     * @param fkAbbreviatedName
     *            the new fk abbreviated name
     */
    public void setFkAbbreviatedName(Long fkAbbreviatedName) {
	this.fkAbbreviatedName = fkAbbreviatedName;
    }

    /**
     * Gets the fk unofficial name.
     *
     * @return the fk unofficial name
     */
    public Long getFkUnofficialName() {
	return fkUnofficialName;
    }

    /**
     * Sets the fk unofficial name.
     *
     * @param fkUnofficialName
     *            the new fk unofficial name
     */
    public void setFkUnofficialName(Long fkUnofficialName) {
	this.fkUnofficialName = fkUnofficialName;
    }

    /**
     * Gets the checks if is managed by cmo.
     *
     * @return the checks if is managed by cmo
     */
    public Boolean getIsManagedByCmo() {
	return isManagedByCmo;
    }

    /**
     * Sets the checks if is managed by cmo.
     *
     * @param isManagedByCmo
     *            the new checks if is managed by cmo
     */
    public void setIsManagedByCmo(Boolean isManagedByCmo) {
	this.isManagedByCmo = isManagedByCmo;
    }

    public List<TerritoryName> getTerritoryNameList() {
	if (territoryNameList == null) {
	    territoryNameList = new ArrayList<>();
	}
	return territoryNameList;
    }

    public void setTerritoryNameList(List<TerritoryName> territoryNameList) {
	this.territoryNameList = territoryNameList;
    }

    public String getTisn() {
	return getCode();
    }

    public String getTypeCode() {
	return typeCode;
    }

    public void setTypeCode(String typeCode) {
	this.typeCode = typeCode;
    }

}
