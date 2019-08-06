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
public class TerritoryNameIdentifierFlat implements Serializable, Identifiable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 175497802726503360L;

    /** The id TerritoryName identifier. */
    private Long idTerritoryNameIdentifier;

    /** The fk fkTerritory Name. */
    private Long fkTerritoryName;

    /** The fk identifier. */
    private Long fkIdentifier;

    /** The code. */
    private String code;

    /** The value. */
    private String value;




    public String getCode() {
        return this.code;
    }


    public Long getFkIdentifier() {
        return this.fkIdentifier;
    }




    public Long getFkTerritoryName() {
        return this.fkTerritoryName;
    }




    @Override
    public Long getId() {
        return getIdTerritoryNameIdentifier();
    }




    public Long getIdTerritoryNameIdentifier() {
        return this.idTerritoryNameIdentifier;
    }



    public String getValue() {
        return this.value;
    }




    public void setCode(String code) {
        this.code = code;
    }



    public void setFkIdentifier(Long fkIdentifier) {
        this.fkIdentifier = fkIdentifier;
    }




    public void setFkTerritoryName(Long fkTerritoryName) {
        this.fkTerritoryName = fkTerritoryName;
    }




    @Override
    public void setId(Long id) {
        setIdTerritoryNameIdentifier(id);
    }




    public void setIdTerritoryNameIdentifier(Long idTerritoryNameIdentifier) {
        this.idTerritoryNameIdentifier = idTerritoryNameIdentifier;
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
