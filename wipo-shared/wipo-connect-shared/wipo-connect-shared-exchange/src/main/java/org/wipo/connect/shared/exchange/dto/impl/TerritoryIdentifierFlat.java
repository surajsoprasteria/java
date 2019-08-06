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

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.wipo.connect.common.dto.Identifiable;



/**
 * The Class InterestedPartyIdentifierFlat.
 */
public class TerritoryIdentifierFlat implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 175497802726503360L;

    /** The id territory identifier. */
    private Long idTerritoryIdentifier;

    /** The fk interested party. */
    private Long fkTerritory;

    /** The fk identifier. */
    private Long fkIdentifier;

    /** The code. */
    private String code;

    /** The value. */
    private String value;



    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return this.code;
    }



    /**
     * Gets the fk identifier.
     *
     * @return the fk identifier
     */
    public Long getFkIdentifier() {
        return this.fkIdentifier;
    }



    /**
     * Gets the fk Territory.
     *
     * @return the fk Territory
     */
    public Long getFkTerritory() {
        return this.fkTerritory;
    }




    @Override
    public Long getId() {
        return getIdTerritoryIdentifier();
    }



    /**
     * Gets the id Territory identifier.
     *
     * @return the id Territory identifier
     */
    public Long getIdTerritoryIdentifier() {
        return this.idTerritoryIdentifier;
    }



    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return this.value;
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
     * Sets the fk identifier.
     *
     * @param fkIdentifier
     *            the new fk identifier
     */
    public void setFkIdentifier(Long fkIdentifier) {
        this.fkIdentifier = fkIdentifier;
    }



    /**
     * Sets the fk interested party.
     *
     * @param fkInterestedParty
     *            the new fk interested party
     */
    public void setFkTerritory(Long fkTerritory) {
        this.fkTerritory = fkTerritory;
    }




    @Override
    public void setId(Long id) {
        setIdTerritoryIdentifier(id);
    }



    /**
     * Sets the id interested party identifier.
     *
     * @param idInterestedPartyIdentifier
     *            the new id interested party identifier
     */
    public void setIdTerritoryIdentifier(Long idTerritoryIdentifier) {
        this.idTerritoryIdentifier = idTerritoryIdentifier;
    }



    /**
     * Sets the value.
     *
     * @param value
     *            the new value
     */
    public void setValue(String value) {
        this.value = value;
    }




    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
